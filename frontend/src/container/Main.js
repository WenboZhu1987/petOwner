import React, { Component } from 'react';
import '../css/App.css';
import axios from 'axios';
import NewPet from '../components/newPet/NewPet';
import { Table, Button } from 'antd';
import './Main.css';


class Main extends Component {

    state = {
        owners: [],
        pets:[],
        showOwner : false,
        showPet: false
    }

    componentDidMount () {
        axios.get("http://localhost:8080/owner/owners").then(res => {
            this.setState({owners:res.data})
        });
        axios.get("http://localhost:8080/pet/pets").then(res => {
            this.setState({pets:res.data})
        });
    }
    
    getOwnerHandler = () => {
        const doesShow = this.state.showOwner;
        this.setState( { showOwner: !doesShow } );
        axios.get("http://localhost:8080/owner/owners").then(res => {
            this.setState({owners:res.data})
        });
    }
    getPetHandler = () => {
        const doesShow = this.state.showPet;
        this.setState( { showPet: !doesShow } );
        axios.get("http://localhost:8080/pet/pets").then(res => {
            this.setState({pets:res.data})
        });
      }

  render() {
    const owners = this.state.owners.map(owner => {
        return {
            ...owner,
            key: owner.id
        }
    });

    const pets = this.state.pets.map(pet => {
        return {
            ...pet,
            key: pet.id
        }
    });
     
      const ownerColumns = [{
        title: 'First Name',
        dataIndex: 'first_name',
        key: 'first_name',
      }, {
        title: 'Last Name',
        dataIndex: 'last_name',
        key: 'last_name',
      }, {
        title: 'City',
        dataIndex: 'city',
        key: 'city',
      }, {
        title: 'Pet Id',
        dataIndex: 'pet_id',
        key: 'pet_id',
      },];

      const petColumns = [{
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
      }, {
        title: 'Birthday',
        dataIndex: 'birthday',
        key: 'birthday',
      }, {
        title: 'Owner Id',
        dataIndex: 'owner_id',
        key: 'owner_id',
      },];

      let ownersTable = null;
      if(this.state.showOwner){
        ownersTable = (
            <Table className = "Table" dataSource={owners} columns={ownerColumns} />
        );
      }
      let petsTable = null;
      if(this.state.showPet){
        petsTable = (
            <Table className = "Table" dataSource={pets} columns={petColumns} />
        );
      }

    return (
      <div className="Main">
          <Button type= "primary" style ={{margin: 20 }} onClick={this.getOwnerHandler}>Get Owners</Button>
          <Button type= "primary" style ={{margin: 20 }} onClick={this.getPetHandler}>Get Pets</Button>
          {ownersTable}
          {petsTable}
       
          <section>
            <NewPet />
          </section>
      </div>
    );
  }
}

export default Main;