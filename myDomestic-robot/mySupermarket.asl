// Agent mySupermarket in project DomesticRobot.mas2j

/* Initial beliefs and rules */

// Identificador de la última orden entregada
last_order_id(1).

//Producto, stock, precio
stock(beer,mahou,math.round(math.random(50))+50,math.round(math.random(9))+10).
stock(beer,estrella,math.round(math.random(50))+50,math.round(math.random(9))+1).
stock(beer,heineken,math.round(math.random(50))+50,math.round(math.random(9))+1).
stock(pincho,tortilla,math.round(math.random(25))+25,math.round(math.random(9))+1).
stock(pincho,empanada,math.round(math.random(25))+25,math.round(math.random(9))+1).


//Precio de adquisción
//Saldo inicial del súper
wallet(math.round(math.random(1000))+500).




/* Initial goals */

//!send_price.

/* Plans */

+!send_price(Product, Brand)<-
	?stock(Product,Brand,S,Price);
	.send(myRobotDel,tell,sprice(mySupermarket,Product,Brand,S,Price)).


+!orderMulti(Product,Brand, Qtd ,BrandB, QtdB,M )[source(Ag)]:stock(Product, Brand, St, _) & stock(Product, BrandB, StB, _)<-
	-stock(Product, Brand,_,Price);
	+stock(Product, Brand, St -Qtd, Price);
	-stock(Product, BrandB,_,PriceB);
	+stock(Product, BrandB, StB -QtdB, PriceB);
	.println("Pedido de ",Qtd," ",Brand," y ",QtdB," ",BrandB," realizadonse.");
	deliver(Product, Qtd+QtdB);
	.send(Ag, tell, deliveredB);
	.send(Ag, achieve, deliveredDos(Product, Brand, Qtd,BrandB,QtdB,OrderId, Qtd*Price+QtdB*PriceB));
	-wallet(Pro);
	+wallet(Pro+Qtd*Price+QtdB*PriceB);
	?wallet(Y);
	.println("el saldo actual es de", Y).
	
+!order(pincho, Brand, Qtd, M)[source(Ag)]: stock(pincho, Brand, A, Price) &  A >= Qtd & Qtd*Price <= M <- 
	-stock(pincho, Brand,_,Price); //elimino la creencia de stock del producto  (ya que si tengo otro producto tb en la creencia stock actualiza el primero sea cual sea el producto)
	+stock(pincho, Brand,A-Qtd,Price); //añado la creencia ya actualizada
	?stock(pincho, Brand,X,Price);
	.println(" tengo un stock de ", X, pincho, Brand);
	.println(" cada ",Brand," cuesta ", Price);
	 .send(Ag, tell, deliveredP);
	.send(Ag,achieve, delivered(pincho, Brand, Qtd,OrderId, Qtd*Price));
	+orderFrom(Ag, Qtd);
	.println("Pedido de ", Qtd," ",Brand, " recibido de ", Ag);
	-wallet(Pro);
	+wallet(Pro+Qtd*Price);
	?wallet(Y);
	.println("el saldo actual es de", Y).

	
+!order(Product, Brand, Qtd, M)[source(Ag)]: stock(Product, Brand, A, Price) &  A >= Qtd & Qtd*Price <= M <- 
	-stock(Product, Brand,_,Price); //elimino la creencia de stock del producto  (ya que si tengo otro producto tb en la creencia stock actualiza el primero sea cual sea el producto)
	+stock(Product, Brand,A-Qtd,Price); //añado la creencia ya actualizada
	?stock(Product, Brand,X,Price);
	.println(" tengo un stock de ", X, Product, Brand);
	.println(" cada ",Brand," cuesta ", Price);
	deliver(Product, Qtd);
	.send(Ag, tell, deliveredB);
	.send(Ag,achieve, delivered(Product, Brand, Qtd,OrderId, Qtd*Price));
	+orderFrom(Ag, Qtd);
	.println("Pedido de ", Qtd," ",Brand, " recibido de ", Ag);
	-wallet(Pro);
	+wallet(Pro+Qtd*Price);
	?wallet(Y);
	.println("el saldo actual es de", Y).
	
//plan para cuando no hay suficiente stock de Product
+!order(Product, Brand, Qtd,M)[source(Ag)]: stock(Product, Brand, A, Price) &  A < Qtd <- 
	?stock(Product, Brand,X,Price);
	//.send(myRobot, tell, no_stock_super(Product, Brand,X));
	.print("Sólo me quedan", X , " ", Product," ",Brand);
	.print("Voy hacer restock de ", Product," ",Brand);
	.send(myProvider, achieve, supply(mySupermarket, Product, Brand, Qtd*2 ));
	//.wait(3000);
	!order(Product, Brand, Qtd, M)[source(Ag)].
	
+!order(Product, Brand, Qtd,M)[source(Ag)]: stock(Product, Brand, A, Price) & Qtd*Price > M<-
	?stock(Product, Brand,_,Price);
	.println(Ag, " no tiene suficiente dinero para comprar");
	.send(Ag, achieve, notEnoughM(Qtd, Price));
	.send(Ag, askOne, wallet(N));
	!order(Product, Brand, Qtd, N)[source(Ag)].
	
	
	
+!restock(Product, Brand, Qtd,Cost)<-
	-stock(Product, Brand,X,Price);
	+stock(Product, Brand,X+Qtd,Price);
	-wallet(Pro);
	+wallet(Pro-Cost);
	?stock(Product, Brand,Y,Price);
	.wait(10);
	?wallet(Z);
	.println("Ahora el stock de ",Product, Brand," es de ", Y);
	.println("Ahora el saldo es de ", Z).
	
	




