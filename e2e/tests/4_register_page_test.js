Feature('Register page test');

Scenario('test register page', ({I}) => {
	I.amOnPage('http://localhost:9080/login');
	I.see('Our StackOverflow');
	I.click('Menu');
	I.waitForText('Ask your questions');
	I.waitForText('View questions list');
	I.waitForText('Your profil');
	I.seeElement('//button[contains(., "search")]');

	I.fillField({id: 'fUsername'}, 'Bob');
	I.fillField('email', 'test@gmail.com');
	I.fillField('firstName', 'Bob');
	I.fillField('lastName', 'Lennon');
	I.fillField({id: 'fPassword'}, secret('1234'));
	I.click('Register');
});
