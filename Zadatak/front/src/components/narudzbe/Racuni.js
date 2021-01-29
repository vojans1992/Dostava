import React from "react";
import { Button, Form } from "react-bootstrap";

import SprintsAxios from "../../apis/SprintsAxios";

class Racun extends React.Component {
    constructor(props) {
        super(props);

        let racun = {

        }

        this.state = {
            racun: racun
        }
    }

    componentDidMount() {
        this.getData();
    }

    async getData() {
        await this.getRacun(this.props.narudzbaId);
    }

    async getRacun(narudzbaId) {
        try {
            let result = await SprintsAxios.get("/racuni/" + this.props.match.params.id)
            if(result && result.status === 200) {
                this.setState({
                    racun: result.data,
                });
            }
        } catch (error) {
            alert("Nije uspelo dobavljanje.")
        }
    }

    render(){
        return (
            <div>
                <h1>Racun {this.state.racun.broj}</h1>

                <p>
                    broj:{this.state.racun.broj}<br/>
                    racun:{this.state.racun.racun}<br/>
                    ukupno:{this.state.racun.ukupno}<br/>
                </p>
            </div>
        )
    }
}

export default Racun;