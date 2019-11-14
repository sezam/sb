const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {customers: [], usluga: null};
		this.handleUslugaChange = this.handleUslugaChange.bind(this);
		this.handleUslugaCreate = this.handleUslugaCreate.bind(this);
	}

    handleUslugaChange(value) {
        this.setState({usluga: value});
	    if(value == "u3"){
            client({method: 'GET', path: '/api/customers'}).done(response => {
                this.setState({customers: response.entity._embedded.customers});
            });
        }
    }

    handleUslugaCreate(s) {
		client({method: 'POST', path: '/api/customers', entity: s,
		    headers: {'Content-Type': 'application/json'}}).done(response => {
			this.setState({usluga: ''});
		});
    }

	componentDidMount() {
	}

	render() {
	    if(this.state.usluga == "u3"){
            return (
                <div>
                    <CustomerList customers={this.state.customers}/>
                    <br/>
                    <CreateUsluga onUslugaCreate={this.handleUslugaCreate}/>
                </div>
            )
	    } else{
            return (
                <div>
                    <UslugaList onUslugaChange={this.handleUslugaChange}/>
                </div>
            )
	    }
    }
}

class CustomerList extends React.Component{
	render() {
		const customers = this.props.customers.map(customer =>
			<Customer key={customer._links.self.href} customer={customer}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Request</th>
					</tr>
					{customers}
				</tbody>
			</table>
		)
	}
}

class UslugaList extends React.Component{
    constructor(props) {
        super(props);
        this.state = {usluga: ''};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleSelect = this.handleSelect.bind(this);
    }

    handleSelect(e) {
        this.setState({usluga: e.target.value});
    }

    handleSubmit(e) {
        this.props.onUslugaChange(this.state.usluga);
        event.preventDefault();
    }

	render() {
		return (
            <form onSubmit={this.handleSubmit}>
                <label>
                  Выберите услугу:
                  <select onChange={this.handleSelect} placeholder="Выберите услугу">
                    <option value="u1">Нереализованная услуга 1</option>
                    <option value="u2">Нереализованная услуга 2</option>
                    <option value="u3">Реализованная услуга</option>
                    <option value="u4">Нереализованная услуга 3</option>
                  </select>
                </label>
                <input type="submit" value="Выбрать" />
            </form>
        )
    }
}

class CreateUsluga extends React.Component{
    constructor(props) {
        super(props);
        this.state = {firstName: '', lastName: '', request: ''};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleSubmit(e) {
        this.props.onUslugaCreate(this.state);
        event.preventDefault();
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
          [name]: value
        });
    }

	render() {
		return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    FirstName:
                    <input type="text" name="firstName" placeholder="имя" value={this.state.firstName} onChange={this.handleInputChange} />
                </label>
                <br/>
                <label>
                    LastName:
                    <input type="text" name="lastName" placeholder="Фамилия" value={this.state.lastName} onChange={this.handleInputChange} />
                </label>
                <br/>
                <label>
                    Request:
                    <input type="text" name="request" placeholder="Запрос" value={this.state.request} onChange={this.handleInputChange} />
                </label>
                <br/>
                <input type="submit" value="Создать" />
            </form>
        )
    }
}

class Customer extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.customer.firstName}</td>
				<td>{this.props.customer.lastName}</td>
				<td>{this.props.customer.request}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
