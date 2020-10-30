Feature('Register page test');

Scenario('test register page', ({I}) => {
	I.amOnPage('http://localhost:9080/login');
	I.see('Codemad');
	I.click('Menu');
	I.waitForText('View questions list');
	I.seeElement('//button[contains(., "search")]');

	I.fillField({id: 'fUsername'}, 'Bob' + Math.floor(Math.random()*10));
	I.fillField('email', 'test@gmail.com' + Math.floor(Math.random()*10));
	I.fillField('firstName', 'Bob' + Math.floor(Math.random()*10));
	I.fillField('lastName', 'Lennon' + Math.floor(Math.random()*10));
	I.fillField({id: 'fPassword'}, secret('Pa$$w0rd'));
	I.fillField({id: 'fConfirmPassword'}, secret('Pa$$w0rd'));
	I.click('Register');
});
