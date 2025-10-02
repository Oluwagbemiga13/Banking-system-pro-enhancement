package com.jindero.banking.features.account;


import com.jindero.banking.features.user.User;
import com.jindero.banking.shared.exception.InsufficientFundsException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    /*UUID je lepší než Long, protože je unikátní napříč všemi systémy a NENÍ předvídatelný.
    (Takže někdo nemůže hádat ID a získat přístup k jinému účtu)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    protected String accountNumber;

    /* BigDecimal je lepší než double pro finanční výpočty, protože poskytuje přesnost a eliminuje chyby zaokrouhlování,
    které mohou nastat při použití double. Například:
        double b = 0.1;
        double c = 0.2;
        System.out.println(b + c);
        Výstup bude 0.30000000000000004 místo očekávaných 0.3
     */
    protected BigDecimal balance;

    //Propojeni Account s User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //konstruktor
    /*Abstract classes should not have public constructors. Constructors of abstract classes can only be called in constructors of their subclasses.
    So there is no point in making them public. The protected modifier should be enough.
    */
    protected Account() {
    }

    /*Abstract classes should not have public constructors. Constructors of abstract classes can only be called in constructors of their subclasses.
    So there is no point in making them public. The protected modifier should be enough.
    */
    protected Account(String accountNumber, double balance, User user, AccountType accountType) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.valueOf(balance);
        this.user = user;
    }

    //Není potřeba mít ty metody abstract, když už máme AccountType enum
    public BigDecimal calculateInterest() {
        return balance.multiply(accountType.getInterestRate());
    }

    public AccountType getAccountType() {
        return accountType;
    }

    //Metody
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Chyba! Zadej částku větší než 0!");
            return;
        }
        balance = balance.add(BigDecimal.valueOf(amount));
        System.out.println("Vloženo: " + amount + " Kč");
    }

    // Metody co mají side effects by měly být void! To že se něco nepovede, se řeší výjimkou.
    // Vyjímka by se měla vyhodit hned kdy nastane, proto je lepší to řešít už tady než v controlleru.
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Chyba! Zadej částku větší než 0!");
        }
        // metoda compareTo vrací -1, 0, nebo 1 pokud je menší, rovno, nebo větší než druhý operand
        if (balance.compareTo(BigDecimal.valueOf(amount)) >= 0) {
            // Pro jednoduchost se dá použít tento konstruktor s defaultní message.
            throw new InsufficientFundsException();
        }
        balance = balance.subtract(BigDecimal.valueOf(amount));
        System.out.println("Vybráno " + amount + " Kč");
    }

    //Getter
    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    //Setter
// Pro ID by neměl být setter, protože by to mohlo vést k nekonzistenci dat. ID by mělo být neměnné po vytvoření entity.
//    public void setId(Long id) {
//        this.id = id;
//    }

// Podle mne to samé, user by se neměl měnit.
//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}
