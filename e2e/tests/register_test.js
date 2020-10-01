const { v4: uuidv4 } = require('uuid');

Feature('register page test');

Scenario('test create account page', ({I}) => {

	I.amOnPage('http://localhost:9080/login');
	I.fillField({id: 'fUsername'}, uuidv4());
	I.fillField('email', uuidv4() + '@example.com');
	I.fillField('firstName', uuidv4());
	I.fillField('lastName', uuidv4());
	I.fillField({id: 'fPassword'}, secret(uuidv4()));
	I.click({css: '#fRegister'});
	I.seeCurrentUrlEquals('http://localhost:9080/');
});
