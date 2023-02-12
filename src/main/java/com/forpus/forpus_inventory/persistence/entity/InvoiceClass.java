package com.forpus.forpus_inventory.persistence.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "invoice", schema = "inventoryaccounting")
public class InvoiceClass {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idInvoice", nullable = false)
    private int idInvoice;
    @Basic
    @Column(name = "idBill", nullable = false)
    private int idBill;
    @Basic
    @Column(name = "idCustomer", nullable = false, length = 20)
    private String idCustomer;
    @Basic
    @Column(name = "bank", nullable = false, length = 20)
    private String bank;
    @Basic
    @Column(name = "cash", nullable = false, length = 20)
    private String cash;
    @Basic
    @Column(name = "taxes", nullable = false, length = 20)
    private String taxes;
    @Basic
    @Column(name = "indebtedness", nullable = false, length = 20)
    private String indebtedness;
    @Basic
    @Column(name = "total", nullable = false, length = 20)
    private String total;
    @Basic
    @Column(name = "date", nullable = false, length = 20)
    private String date;
    @Basic
    @Column(name = "totalBuy", nullable = false, length = 20)
    private String totalBuy;
    @Basic
    @Column(name = "utilities", nullable = false, length = 20)
    private String utilities;
    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCompanyNIT", nullable = false, insertable = false, updatable = false)
    private CompanyClass companyByIdCustomer;
    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer", nullable = false, insertable = false, updatable = false)
    private CustomerClass customerByIdCustomer;
    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "IdentificationCard", nullable = false, insertable = false, updatable = false)
    private PartnersClass partnersByIdCustomer;
    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "Nit", nullable = false, insertable = false, updatable = false)
    private ProvidersClass providersByIdCustomer;
    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "IdentificationCard", nullable = false, insertable = false, updatable = false)
    private WorkersClass workersByIdCustomer;
    @OneToMany(mappedBy = "invoiceByIdInvoice")
    private Collection<WareinvoiceClass> wareinvoicesByIdInvoice;

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public String getIndebtedness() {
        return indebtedness;
    }

    public void setIndebtedness(String indebtedness) {
        this.indebtedness = indebtedness;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(String totalBuy) {
        this.totalBuy = totalBuy;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceClass that = (InvoiceClass) o;
        return idInvoice == that.idInvoice && idBill == that.idBill && Objects.equals(idCustomer, that.idCustomer) && Objects.equals(bank, that.bank) && Objects.equals(cash, that.cash) && Objects.equals(taxes, that.taxes) && Objects.equals(indebtedness, that.indebtedness) && Objects.equals(total, that.total) && Objects.equals(date, that.date) && Objects.equals(totalBuy, that.totalBuy) && Objects.equals(utilities, that.utilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInvoice, idBill, idCustomer, bank, cash, taxes, indebtedness, total, date, totalBuy, utilities);
    }

    public CompanyClass getCompanyByIdCustomer() {
        return companyByIdCustomer;
    }

    public void setCompanyByIdCustomer(CompanyClass companyByIdCustomer) {
        this.companyByIdCustomer = companyByIdCustomer;
    }

    public CustomerClass getCustomerByIdCustomer() {
        return customerByIdCustomer;
    }

    public void setCustomerByIdCustomer(CustomerClass customerByIdCustomer) {
        this.customerByIdCustomer = customerByIdCustomer;
    }

    public PartnersClass getPartnersByIdCustomer() {
        return partnersByIdCustomer;
    }

    public void setPartnersByIdCustomer(PartnersClass partnersByIdCustomer) {
        this.partnersByIdCustomer = partnersByIdCustomer;
    }

    public ProvidersClass getProvidersByIdCustomer() {
        return providersByIdCustomer;
    }

    public void setProvidersByIdCustomer(ProvidersClass providersByIdCustomer) {
        this.providersByIdCustomer = providersByIdCustomer;
    }

    public WorkersClass getWorkersByIdCustomer() {
        return workersByIdCustomer;
    }

    public void setWorkersByIdCustomer(WorkersClass workersByIdCustomer) {
        this.workersByIdCustomer = workersByIdCustomer;
    }

    public Collection<WareinvoiceClass> getWareinvoicesByIdInvoice() {
        return wareinvoicesByIdInvoice;
    }

    public void setWareinvoicesByIdInvoice(Collection<WareinvoiceClass> wareinvoicesByIdInvoice) {
        this.wareinvoicesByIdInvoice = wareinvoicesByIdInvoice;
    }
}
