Feature('home page test');

Scenario('test home page', ({I}) => {
	I.amOnPage('http://localhost:9080/');
	I.see('Our StackOverflow');
	I.click('Menu');
	I.waitForText('Ask your questions');
	I.waitForText('Your profil');
	I.seeElement('//button[contains(., "search")]');
	I.clickLink('Sign up/in');
	I.seeCurrentUrlEquals('http://localhost:9080/login');
});
