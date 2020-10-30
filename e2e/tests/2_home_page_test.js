Feature('home page test');

Scenario('test home page', ({I}) => {
	I.amOnPage('http://localhost:9080/');
	I.see('Codemad');
	I.click('Menu');
	I.waitForText('View questions list');
	I.seeElement('//button[contains(., "search")]');
	I.clickLink('Sign in');
	I.seeCurrentUrlEquals('http://localhost:9080/login');
});
