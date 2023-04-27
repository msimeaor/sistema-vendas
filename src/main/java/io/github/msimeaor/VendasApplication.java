package io.github.msimeaor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication {

    /*
    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
        return args -> {

            Cliente c = new Cliente("MATHEUS SIMEAO DOS REIS");
            clientes.save(c);

            Pedido p = new Pedido();
            p.setCliente(c);
            p.setData_pedido(LocalDate.now());
            p.setTotal(new BigDecimal("467.45"));
            pedidos.save(p);

            Pedido p2 = new Pedido();
            p2.setCliente(c);
            p2.setData_pedido(LocalDate.now());
            p2.setTotal(new BigDecimal("221.87"));
            pedidos.save(p2);

            // System.out.println(clientes.findClienteFetchPedidos(c.getId()).getPedidos());
            pedidos.findByCliente(c).forEach(pedido -> {
                System.out.println(pedido);
            });

        };
    }
    */

  public static void main(String[] args) {
    SpringApplication.run(VendasApplication.class, args);
  }

}
