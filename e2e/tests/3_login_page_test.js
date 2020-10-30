Feature('login page test');

Scenario('test login page', ({I}) => {
	I.amOnPage('http://localhost:9080/login');
	I.see('Codemad');
	I.click('Menu');
	I.waitForText('View questions list');
	I.seeElement('//button[contains(., "search")]');

	I.fillField({id: 'fLoginUsername'}, 'bob');
	I.fillField({id: 'fLoginPassword'}, secret('1234'));
	I.click('Login');
});
