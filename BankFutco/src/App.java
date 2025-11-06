import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
import model.Account;
import model.Balance;
import model.Cards;
import model.Loans;
import repositories.AccountRepository;
import repositories.BalanceRepository;
import repositories.CardsRepository;
import repositories.LoansRepository;



public class App {
    private static final Scanner scanner = new Scanner(System.in);


    private static final AccountRepository accountRepository = new AccountRepository();
    private static final BalanceRepository balanceRepository = new BalanceRepository();
    private static final CardsRepository cardsRepository = new CardsRepository();
    private static final LoansRepository loansRepository = new LoansRepository();

    public static void main(String[] args) throws Exception {
        boolean exit = false;

        while (!exit) {
            System.out.println("");
            System.out.println("-----MENU PRINCIPAL-----");
            System.out.println("1. CUENTAS");
            System.out.println("2. TARJETAS");
            System.out.println("3. SALDOS");
            System.out.println("4. PRESTAMOS");
            System.out.println("5. salir");
            System.out.print("seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> menuAccounts();
                case 2 -> menuCards();
                case 3 -> menuBalance();
                case 4 -> menuLoans();
                case 5 -> exit = true;
                default -> System.out.println("la opcion es invalida");


            }

            
        }
        
    }

    private static void menuAccounts(){
        boolean goBack = false;

        while (!goBack) {
            System.out.println("");
            System.out.println("-----MENU ACCOUNTS-----");
            System.out.println("1. Create");
            System.out.println("2. Read By Id");
            System.out.println("3. List All");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Volver");
            System.out.print("seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 ->{
                    System.out.print("Ingrese numero de cuenta: ");
                    String id = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Gmail: ");
                    String email= scanner.nextLine();
                    System.out.print("Telefono: ");
                    String phone = scanner.nextLine();
                    System.out.print("Tipo de cuenta : ");
                    String type = scanner.nextLine();
                    System.out.print("Direccion: ");
                    String address = scanner.nextLine();

                    Account cuenta = new Account(id, name, email, phone, type, address);
                    accountRepository.save(cuenta);
                    System.out.println("Cuenta crada correctamente");
                    
                }
                case 2 ->{
                    System.out.print("Ingresa el numero de cuenta: ");
                    String id = scanner.nextLine();
                    Optional<Account> encontrar = accountRepository.findById(id);
                    System.out.println(encontrar.map(Object::toString).orElse("se encontro la cuenta"));
                
                }
                case 3 -> accountRepository.findAll().forEach(System.out::println);
                case 4 ->{
                    System.out.print("Ingrese el numero de cuenta que quieres actualizar: ");
                    String id = scanner.nextLine();
                    Optional<Account> existe = accountRepository.findById(id);

                    if (existe.isPresent()) {
                        Account cuenta = existe.get();
                        System.out.println("El nuevo nombre ("+ cuenta.getName()+"):");
                        cuenta.setName(scanner.nextLine());
                        accountRepository.save(cuenta);
                        System.out.println("Cuenta actualiza con...");
                        
                    }else{
                        System.out.println("Cuenta no encontrada");

                    }
                }
                case 5 ->{
                    System.out.print("numero de cuenta que quieres eliminar: ");
                    String id = scanner.nextLine();

                    if (accountRepository.deleteById(id)) {
                        System.out.println("La cuenta fue eliminada correctamente");
                    }else{
                        System.out.println("La cuenta no fue encontrada");
                    }
                }

                case 0 -> goBack = true;
                default -> System.out.println("la opcion es invalida");

            }
            
        }

    }

    private static void menuCards(){
        boolean goBack = false;

        while (!goBack) {
            System.out.println("");
            System.out.println("-----MENU CARDS-----");
            System.out.println("1. Create");
            System.out.println("2. Read By Id");
            System.out.println("3. List All");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Volver");
            System.out.print("seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 ->{
                    System.out.print("Ingrese el numero de tarjeta: ");
                    String id = scanner.nextLine();
                    System.out.print("ingrese el tipo de tarjeta (Debito, Credito): ");
                    String type = scanner.nextLine();
                    System.out.print("Ingrese el limite total: ");
                    BigDecimal totalLimit = new BigDecimal(scanner.nextLine());
                    Cards tarjeta = new Cards(id, type, totalLimit, BigDecimal.ZERO, totalLimit);
                    cardsRepository.save(tarjeta);
                    System.out.println("Su targeta fue creada exitosamente");
                }

                case 2 ->{
                    System.out.print("Ingresa el numero de tarjeta: ");
                    String id = scanner.nextLine();
                    Optional<Cards> encontrar = cardsRepository.findById(id);
                    System.out.println(encontrar.map(Object::toString).orElse("NO se encontro la cuenta"));

                }

                case 3 -> cardsRepository.findAll().forEach(System.out::println);

                case 4 -> {
                    System.out.print("Ingrese el numero de la tarjeta que quieres actualizar: ");
                    String id = scanner.nextLine();
                    var tarjeta = cardsRepository.findById(id);
                    if (tarjeta.isPresent()) {
                        Cards cards = tarjeta.get();
                        System.out.println("El nuevo limite("+ cards.getTotalLimit()+"): ");
                        cards.setTotalLimit(new BigDecimal(scanner.nextLine()));
                        cardsRepository.save(cards);
                        System.out.println("Tarjeta actulizada con exito ");
                        
                    }else{
                        System.out.println("La tarjeta no se encontro");
                    }
                    
                    
                }

                case 5 ->{
                    System.out.println("Ingresa el numero de la tarjeta que quieres eliminar: ");
                    String id = scanner.nextLine();
                    if (cardsRepository.deleteById(id))
                        System.out.println(" Tarjeta eliminada correctamente");
                    else
                        System.out.println("La Tarjeta no fue encontrada");
                    }

                    case 0 -> goBack = true;
                default -> System.out.println("la opcion es invalida");

            }

                    
        }

            
    }

    private static void menuBalance() {
        boolean goBack = false;
        while (!goBack) {
            System.out.println("");
            System.out.println("--- MENÚ BALANCE ---");
            System.out.println("1. Create");
            System.out.println("2. Read by date");
            System.out.println("3. List all");
            System.out.println("4. Delete");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(scanner.nextLine());
                    System.out.print("Descripción: ");
                    String desc = scanner.nextLine();
                    System.out.print("Ingresos: ");
                    BigDecimal in = new BigDecimal(scanner.nextLine());
                    System.out.print("Egresos: ");
                    BigDecimal out = new BigDecimal(scanner.nextLine());
                    System.out.print("Saldo final: ");
                    BigDecimal close = new BigDecimal(scanner.nextLine());
                    balanceRepository.save(new Balance(fecha, desc, in, out, close));
                    System.out.println("El balance fue creado correctamente.");
                }

                case 2 -> {
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.println(balanceRepository.findById(date)
                            .map(Object::toString)
                            .orElse("El Balance no fue encontrado."));
                }

                case 3 -> balanceRepository.findAll().forEach(System.out::println);

                case 4 -> {
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    if (balanceRepository.deleteById(date))
                        System.out.println("El balance eliminado correctamente.");
                    else
                        System.out.println("El balance no fue encontrado.");
                }

                case 0 -> goBack = true;
                default -> System.out.println("la opcion es invalida");
            }
        }
    }

    private static void menuLoans(){
        boolean goBack = false; 

        while (!goBack) {
            System.out.println("");
            System.out.println("--- MENÚ LOANS ---");
            System.out.println("1. Create");
            System.out.println("2. Read by date");
            System.out.println("3. List all");
            System.out.println("4. Delete");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    LocalDate d = LocalDate.parse(scanner.nextLine());
                    System.out.print("Tipo: ");
                    String t = scanner.nextLine();
                    System.out.print("Monto total: ");
                    BigDecimal total = new BigDecimal(scanner.nextLine());
                    System.out.print("Monto pagado: ");
                    BigDecimal paid = new BigDecimal(scanner.nextLine());
                    System.out.print("Monto pendiente: ");
                    BigDecimal out = new BigDecimal(scanner.nextLine());
                    loansRepository.save(new Loans(d, t, total, paid, out));
                    System.out.println("El prestamo fue creado corectamente.");
                }

                case 2 -> {
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.println(loansRepository.findById(date)
                            .map(Object::toString)
                            .orElse("prestamo no encontrado."));
                }

                case 3 -> loansRepository.findAll().forEach(System.out::println);

                case 4 -> {
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    if (loansRepository.deleteById(date))
                        System.out.println(" Préstamo eliminado correctamente.");
                    else
                        System.out.println("prestamo NO encontrado.");
                }

                case 0 -> goBack = true;
                default -> System.out.println("opcion es invalida");
            }

            
        }
    }
}