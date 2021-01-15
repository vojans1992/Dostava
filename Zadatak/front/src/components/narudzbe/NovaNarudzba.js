import React from "react";
import { Button, Form } from "react-bootstrap";

import SprintsAxios from "../../apis/SprintsAxios";

class NovaNarudzba extends React.Component {
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
    
        this.state = {
          //sve sto se koristi na ovoj stranici kao stanje 
          narudzba: narudzba,
          narudzbe: [],
          dostavljaci: [],
          search: { mesto: "", dostavljacId: -1 },
          pageNum: 0,
          totalPages: 1,
        };
      }

      componentDidMount() {
        this.getData();
      }

      async getData() {
        await this.getDostavljaci();
        await this.getNarudzbe();
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

      async doAdd() {
        //dodaje nov entitet http metodom post, salje entitet koji ima atribute dto klase na backu
        try {
          await SprintsAxios.post("/narudzbe/", this.state.narudzba);
            
          this.props.history.push("/narudzbe/");
          
        } catch (error) {
          alert("Nije uspelo dodavanje.");
        }
      }

      valueInputChange(event) {
        let control = event.target;
    
        let name = control.name;
        let value = control.value;
    
        let narudzba = this.state.narudzba;
        narudzba[name] = value;
    
        this.setState({ narudzba: narudzba });
      }

      render (){
          return(
              <div>
                  <Form>
                        <Form.Group>
                            <Form.Label>Broj narudzbe</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="broj"
                            value={this.state.narudzba.broj}
                            as="input"
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Datum</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="datum"
                            value={this.state.narudzba.datum}
                            as="input"
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Mesto isporuke</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="mesto"
                            value={this.state.narudzba.mesto}
                            as="input"
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Cena narudzbe</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="cena"
                            value={this.state.narudzba.cena}
                            as="input"
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Opis</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="opis"
                            value={this.state.narudzba.opis}
                            as="input"
                            ></Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Dostavljac</Form.Label>
                            <Form.Control
                            onChange={(event) => this.addValueInputChange(event)}
                            name="dostavljacId"
                            value={this.state.narudzba.dostavljacId}
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
                        <Button variant="primary" onClick={() => this.doAdd()}>
                            Kreiraj
                        </Button>
                        </Form>
              </div>
          )
      }
}

export default NovaNarudzba;