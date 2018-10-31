import React, { Component } from 'react';
import Main from './container/Main';
import './css/App.css';

class App extends Component {

  render() {
    return (
    <div className="App">
      <h1>PetOwner Demo</h1>
      <hr /><br />
      <Main />
    </div>
  );
  }
}

export default App;