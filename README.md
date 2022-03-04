# POS in Shell

The demo shows a simple POS system with command line interface. 

To run

```shell
mvn clean spring-boot:run
```

Currently, it implements three commands which you can see using the `help` command.

```shell
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.7)
 
shell:>help
AVAILABLE COMMANDS

Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.
        history: Display or save the history of previously run commands
        script: Read and execute commands from a file.
        stacktrace: Display the full stacktrace of the last error.

Pos Command
        a: Add a Product to Cart
        n: New Cart
        p: List Products
```

Everytime a customer come to make a purchase, use `n` to create a new cart and then use `a ${productid} ${amount}` to add a product to the cart.

Please make the POS system robust and fully functional by implementing more commands, for instance, print/empty/modify cart.

Implementing a PosDB with real database is very much welcome. 

Please use asciinema (https://asciinema.org) to record a demo and submit the url in QQ group. 

And please elaborate your understanding in layered systems via this homework in your README.md.

# comments

I implemented 5 commands: `print`, `empty`, `modify`, `checkout` and `total`.

Concerning the understanding of Layered system, I will take the execution of `modify` command as an example:
```
shell:> m "PD1" 6                       // user interacts with Presentation Layer(Shell)
PosCommand::modifyCart("PD1", 6)        // Presentation Layer passes request to Business Logic Layer(PosCommand.class)
PosService::modifyCart("PD1", 6)        // PosService.class is also in Business Logic Layer
Cart::modifyCart("PD1", 6)              // Business Logic Layer passes data to Data Access Layer(Cart.class)
Cart modify Cart Database(in memory)    // Data Access Layer accesses data
```
