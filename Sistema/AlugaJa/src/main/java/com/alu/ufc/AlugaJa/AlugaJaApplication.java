package ;

import com.alu.ufc.AlugaJa.service.impl.ImovelServiceIMPL;
import com.google.cloud.firestore.CollectionReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class AlugaJaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlugaJaApplication.class, args);
		ImovelServiceIMPL imovel = new ImovelServiceIMPL();
		//imovel.getCollection();
	}

}
