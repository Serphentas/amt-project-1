Feature('register page test');

Scenario('test create account page', (I) => {
	
	I.amOnPage('http://localhost:9080/login');
	I.fillField({id: 'fUsername'}, 'bbob');
	I.fillField('email', 'test@gmail.com');
	I.fillField('firstName', 'Bob');
	I.fillField('lastName', 'Lennon');
	I.fillField({id: 'fPassword'}, secret('1234'));
	I.click({css: '#fRegister'});
	I.seeCurrentUrlEquals('http://localhost:9080/');
});
