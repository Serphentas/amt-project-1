Feature('page existing');

Scenario('test if the page exist', ({I}) => {
	I.amOnPage('http://localhost:9080/');
	I.see('Our StackOverflow');
	I.amOnPage('http://localhost:9080/login');
	I.see('Our StackOverflow');
	I.amOnPage('http://localhost:9080/profil');
	I.see('Our StackOverflow');
	I.amOnPage('http://localhost:9080/questions');
	I.see('Our StackOverflow');
});
