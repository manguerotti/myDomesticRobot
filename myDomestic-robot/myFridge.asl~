// Agent myFridge in project DomesticRobot.mas2j

/* Initial beliefs and rules */

/* Initial goals */

stock(beer,mahou,5).
stock(pincho, tortilla, 5).

/* Plans */

+!getOne(P, Brand): stock(P,Brand,Qtd) & Qtd > 0<- 
	-stock(P, Brand, S);
	+stock(P, Brand, S-1).

+!addStock(P, Brand, Qtd): stock(P, Brand, _)<-
	-stock(P,Brand, Q);
	+stock(P,Brand, Q+Qtd).
	
+!addStock(P, Brand, Qtd): not stock(P, Brand, _)<-
	+stock(P,Brand,Qtd).
	
	

