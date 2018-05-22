package pl.wpam.expensesmanager.model;

public class Expense {
    private String type;
    private String date;
    private Double amount;
    private Integer id;
    private String currency;
    private Double exchangeRate;

    public Expense(Double amount, String type, String date, String currency, Double exchangeRate) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public Expense(Integer id, Double amount, String type, String date, String currency, Double exchangeRate) {
        this(amount, type, date, currency, exchangeRate);
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }
}
