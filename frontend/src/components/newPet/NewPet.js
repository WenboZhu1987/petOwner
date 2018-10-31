import React, { Component } from 'react';
import axios from 'axios';
import { DatePicker, Input, Button } from 'antd';

import './NewPet.css';

class NewPet extends Component {

    state = {
        name: '',
        birthday: '',
        first_name: '',
        last_name: '',
        city: ''
    }

    postDataHandler = () => {
        const data = {
            name: this.state.name,
            birthday: this.state.birthday,
            first_name: this.state.first_name,
            last_name: this.state.last_name,
            city: this.state.city
        };
        
        axios.post('http://localhost:8080/pet/pets', data)
            .then(res => {
                if(res.data !== ""){
                    alert("Successful Add Pet!") 
                }else {
                    alert("Invalid Input, Please input all the fields " );
                }
              }
              );
    }

    handleChange(value) {
        if(value !== null){
            this.setState({
                birthday: value.format('YYYY-MM-DD')
            });
        }
      }

    render () {
        return (
            <div className="NewPet">
                <h1>Add a Pet</h1>
                <label>Name</label>
                <Input style ={{width:400}} type="text" value={this.state.name} onChange={(event) => this.setState({name: event.target.value})} />
                <label>Birthday</label>
                <DatePicker style ={{width:500}}
                onChange={value=>this.handleChange(value)} />
                <label>Owner First Name</label>
                <Input style ={{width:400}} type="text" value={this.state.first_name} onChange={(event) => this.setState({first_name: event.target.value})} />
                <label>Owner Last Name</label>
                <Input  style ={{width:400}} type="text" value={this.state.last_name} onChange={(event) => this.setState({last_name: event.target.value})} />
                <label>Owner City</label>
                <Input style ={{width:400}} type="text" value={this.state.city} onChange={(event) => this.setState({city: event.target.value})} />
                <Button type= "primary" style ={{margin: 20 }} onClick={this.postDataHandler}>Add Pet</Button>
            </div>
        );
    }
}

export default NewPet;