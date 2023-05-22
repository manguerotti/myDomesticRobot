// Agent myProvider in project DomesticRobot.mas2j

/* Initial beliefs and rules */

cost(beer,mahou,math.round(math.random(4))+1).
cost(beer,estrella,math.round(math.random(4))+1).
cost(beer,heineken,math.round(math.random(4))+1).
cost(pincho,tortilla,math.round(math.random(4))+1).
cost(pincho,empanada,math.round(math.random(4))+1).


/* Initial goals */


/* Plans */

+!supply(Ag, Product, Brand, Qtd )<-
	.abolish(cost(Product, Brand, _));
	+cost(Product, Brand,math.round(math.random(4))+1);
	.wait(100);
	?cost(Product, Brand, N);
	.send(Ag, achieve, restock(Product, Brand, Qtd,Qtd*N)).

	
