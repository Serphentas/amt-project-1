Feature('login page test');

Scenario('test login page', ({I}) => {
	I.amOnPage('http://localhost:9080/login');
	I.see('Our StackOverflow');
	I.click('Menu');
	I.waitForText('Ask your questions');
	I.waitForText('Your profil');
	I.seeElement('//button[contains(., "search")]');

	I.fillField({id: 'fLoginUsername'}, 'bob');
	I.fillField({id: 'fLoginPassword'}, secret('1234'));
	I.click('Login');
});
