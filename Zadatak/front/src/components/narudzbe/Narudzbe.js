import React from "react";
import { Table, Button, Form, ButtonGroup } from "react-bootstrap";

import SprintsAxios from "../../apis/SprintsAxios";

class Narudzbe extends React.Component {
  constructor(props) {
    super(props);

    let narudzba = {
      // default vrednosti od nesto (kao dto klasa)
      datum: "",
      mesto: "",
      cena: 0.00,
      opis: "",
      dostavljacId: -1,
      dostavljacIme: "",
    };

    let racun = {}

    this.state = {
      //sve sto se koristi na ovoj stranici kao stanje 
      racun: racun,
      narudzba: narudzba,
      narudzbe: [],
      dostavljaci: [],
      search: { mesto: "", dostavljacId: -1 },
      pageNum: 0,
      totalPages: 1,
    };
  }

  componentDidMount() {
  // dobavlja sve iz baze sto treba
    this.getData();
  }

  async getData() {
    await this.getDostavljaci();
    await this.getNarudzbe();

  }
  async getRacun(id){
    try {
      let result = await SprintsAxios.get("/racuni" + id);
      if (result && result.status === 200) {
        this.setState({
          racun: result.data,
          totalPages: result.headers["total-pages"],
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje.");
    }
  }

  async getNarudzbe(page = null) {
    // u config se ubacuju vrednosti koje ce se koristiti za pretragu na backu, ako nista nije uneto u search na frontu na backu ce biti default vrednosti
    let config = { params: {} };

    //ifovi su ako se trazi preko pretrage, ako nije onda se koriste default vrednosti

    if (this.state.search.mesto != "") {
      config.params.mesto = this.state.search.mesto;
    }

    if (this.state.search.dostavljacId != -1) {
      config.params.dostavljacId = this.state.search.dostavljacId;
    }

    if (page != null) {
      config.params.pageNum = page;
    } else {
      config.params.pageNum = this.state.pageNum;
    }

    try {
      let result = await SprintsAxios.get("/narudzbe", config);
      if (result && result.status === 200) {
        this.setState({
          narudzbe: result.data,
          totalPages: result.headers["total-pages"],
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje.");
    }
  }

  async getDostavljaci() {
    //kao i dobavljanje za glavnu klasu samo sto ovde nema pretrage
    try {
      let result = await SprintsAxios.get("/dostavljaci");
      if (result && result.status === 200) {
        this.setState({
          dostavljaci: result.data,
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje.");
    }
  }

  goToAdd(narudzbaId) {
    //gura na stranicu za edit, prima nestoId po kom ce naci taj entitet na backu
    this.props.history.push("/narudzbe/add/" + narudzbaId);
  }

  

  async doDelete(narudzbaId) {
    //brise entitet sa primljenim id-em http metodom delete i dobavlja opet sve entitete bez obrisanog
    try {
      await SprintsAxios.delete("/narudzbe/" + narudzbaId);
      this.getNarudzbe();
    } catch (error) {
      alert("Nije uspelo brisanje.");
    }
  }

  

  searchValueInputChange(event) {
    //poprilicno slicna metoda kao addValue... osim sto ne menja entitet na stanju nego parametre pretrage koji se prosledjuju metodi doSearch pa na osnovu njih radi pretragu
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;

    this.doSearch();
    this.setState({ search: search });
  }

  doSearch() {
    this.setState({ totalPages: 1, pageNum: 0 });
    //ako se ne varam prosledjuje nulu kad poseban parametar da bi back znao kako da se ponasa(treba da bude razlicito od null... ali nisam siguran)
    this.getNarudzbe(0);
  }

  async kreiraj(id) {
    //metoda za menjanje stanja(u zadatku sa sprintovima (nov, u toku, zavrsen) pa se koristi http metoda post za izmenu postojeceg entiteta) pa se ponovo uzimaju svi entiteti sa promenjenim
    try {
      await SprintsAxios.post(`/narudzba/${id}/kreiraj`);
      this.getNarudzbe();
    } catch (error) {
      alert("Nije moguće promeniti stanje.");
    }
  }

  changePage(direction) {
    //metoda za promenu stranice prima 1 i -1 da zna da li stranica ispred ili stranica iza treba da se dobavi
    let page = this.state.pageNum + direction;
    this.getNarudzbe(page);
    this.setState({ pageNum: page });
    //this.setState({pageNum: page}, this.getZadaci);
  }

  render() {
    return (
      <div>
        <h1>Nesto</h1>

        <Form style={{marginTop:35}}>
          <Form.Group>
            <Form.Label>Dostavljac</Form.Label>
            <Form.Control
              onChange={(event) => this.searchValueInputChange(event)}
              name="dostavljacId"
              value={this.state.search.dostavljacId}
              as="select"
            >
              <option value={-1}></option>
              {this.state.dostavljaci.map((dostavljac) => {
                return (
                  <option value={dostavljac.id} key={dostavljac.id}>
                    {dostavljac.ime}
                  </option>
                );
              })}
            </Form.Control>
          </Form.Group>
          <Form.Group>
            <Form.Label>Mesto isporuke</Form.Label>
            <Form.Control
              value={this.state.search.mesto}
              name="mesto"
              as="input"
              onChange={(e) => this.searchValueInputChange(e)}
            ></Form.Control>
          </Form.Group>
          <Button onClick={() => this.doSearch()}>Pretraga</Button>
        </Form>

        <ButtonGroup style={{ marginTop: 25 }}>
          <Button
            disabled={this.state.pageNum == 0}
            onClick={() => this.changePage(-1)}
          >
            Prethodna
          </Button>
          <Button
            disabled={this.state.pageNum + 1 == this.state.totalPages}
            onClick={() => this.changePage(1)}
          >
            Sledeća
          </Button>
        </ButtonGroup>

        <Table bordered striped style={{ marginTop: 5 }}>
          <thead className="thead-dark">
            <tr>
              <th>Broj narudzbe</th>
              <th>Datum</th>
              <th>Mesto isporuke</th>
              <th>Cena</th>
              <th>Opis</th>
              <th>Dostavljac</th>
              <th colSpan={2}></th>
            </tr>
          </thead>
          <tbody>
            {this.state.narudzbe.map((narudzba) => {
              return (
                <tr key={narudzba.id}>
                  <td>{narudzba.broj}</td>
                  <td>{narudzba.datum}</td>
                  <td>{narudzba.mesto}</td>
                  <td>{narudzba.cena}</td>
                  <td>{narudzba.opis}</td>
                  <td>{narudzba.dostavljacIme}</td>
                  <td>
                    <Button
                      //disabled={narudzba.id === 3}
                      variant="info"
                      onClick={() => this.kreiraj(narudzba.id)}
                    >
                      Kreiraj 
                    </Button>

                    <Button
                      variant="warning"
                      onClick={() => this.goToAdd(narudzba.id)}
                      style={{ marginLeft: 5 }}
                    >
                      Dodaj
                    </Button>

                    <Button
                      variant="danger"
                      onClick={() => this.doDelete(narudzba.id)}
                      style={{ marginLeft: 5 }}
                    >
                      Obrisi
                    </Button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default Narudzbe;
