Feature('page existing');

Scenario('test if the page exist', ({I}) => {
	I.amOnPage('http://localhost:9080/');
	I.seeCurrentUrlEquals('http://localhost:9080/');
	I.see('Codemad');

	I.amOnPage('http://localhost:9080/login');
	I.seeCurrentUrlEquals('http://localhost:9080/login');
	I.see('Codemad');

	I.amOnPage('http://localhost:9080/questions');
	I.seeCurrentUrlEquals('http://localhost:9080/questions');
	I.see('Codemad');
});
