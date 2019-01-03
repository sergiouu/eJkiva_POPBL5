

public class main {
	static Robot robot;
	Movimiento movimiento;
	//Scanner teclado;
	public main(){
		robot = new Robot(2,0);
		movimiento=new Movimiento(robot,2,1);
		movimiento.move();
		//teclado = new Scanner (System.in);
	}

	public static void main(String[] args) {
		main mains =new main();

	}
	}
