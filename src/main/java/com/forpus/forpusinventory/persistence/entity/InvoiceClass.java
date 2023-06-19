package com.forpus.forpusinventory.persistence.entity;

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
    @Column(name = "idBill", nullable = true)
    private int idBill;
    @Basic
    @Column(name = "idCustomer", nullable = true, length = 20)
    private String idCustomer;
    @Basic
    @Column(name = "idCompany", nullable = true, length = 20)
    private String idCompany;
    @Basic
    @Column(name = "idPartners", nullable = true, length = 20)
    private String idPartners;
    @Basic
    @Column(name = "idProviders", nullable = true, length = 20)
    private String idProviders;
    @Basic
    @Column(name = "idWorkers", nullable = true, length = 20)
    private String idWorkers;
    @Basic
    @Column(name = "bank", nullable = false, length = 20)
    private String bank;
    @Basic
    @Column(name = "cash", nullable = false, length = 20)
    private String cash;
    @Basic
    @Column(name = "taxes", nullable = true, length = 20)
    private String taxes;
    @Basic
    @Column(name = "bold", nullable = true, length = 20)
    private String bold;
    @Basic
    @Column(name = "indebtedness", nullable = true, length = 20)
    private String indebtedness;
    @Basic
    @Column(name = "total", nullable = false, length = 20)
    private String total;
    @Basic
    @Column(name = "date", nullable = false, length = 30)
    private String date;
    @Basic
    @Column(name = "totalBuy", nullable = true, length = 20)
    private String totalBuy;
    @Basic
    @Column(name = "utilities", nullable = true, length = 20)
    private String utilities;
    @Basic
    @Column(name = "rUtilities", nullable = true, length = 20)
    private String rUtilities;
    @Basic
    @Column(name = "observations", nullable = true, length = 100)
    private String observations;
    @ManyToOne
    @JoinColumn(name = "idCompany", referencedColumnName = "idCompanyNIT", nullable = true, insertable = false, updatable = false)
    private CompanyClass companyByIdCompany;
    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer", nullable = true, insertable = false, updatable = false)
    private CustomerClass customerByIdCustomer;
    @ManyToOne
    @JoinColumn(name = "idPartners", referencedColumnName = "IdentificationCard", nullable = true, insertable = false, updatable = false)
    private PartnersClass partnersByIdPartners;
    @ManyToOne
    @JoinColumn(name = "idProviders", referencedColumnName = "Nit", nullable = true, insertable = false, updatable = false)
    private ProvidersClass providersByIdProviders;
    @ManyToOne
    @JoinColumn(name = "idWorkers", referencedColumnName = "IdentificationCard", nullable = true, insertable = false, updatable = false)
    private WorkersClass workersByIdWorkers;
    @OneToMany(mappedBy = "invoiceByIdInvoice")
    private Collection<WareinvoiceClass> wareinvoicesByIdInvoice;

    @OneToMany(mappedBy = "invoiceByIdInvoice")
    private Collection<MoveinvoiceClass> moveinvoicesByIdInvoice;

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

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public String getIdPartners() {
        return idPartners;
    }

    public void setIdPartners(String idPartners) {
        this.idPartners = idPartners;
    }

    public String getIdProviders() {
        return idProviders;
    }

    public void setIdProviders(String idProviders) {
        this.idProviders = idProviders;
    }

    public String getIdWorkers() {
        return idWorkers;
    }

    public void setIdWorkers(String idWorkers) {
        this.idWorkers = idWorkers;
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

    public String getRUtilities() {
        return rUtilities;
    }

    public void setRUtilities(String rUtilities) {
        this.rUtilities = rUtilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceClass that = (InvoiceClass) o;
        return idInvoice == that.idInvoice && idBill == that.idBill && Objects.equals(idCustomer, that.idCustomer) && Objects.equals(idCompany, that.idCompany) && Objects.equals(idPartners, that.idPartners) && Objects.equals(idProviders, that.idProviders) && Objects.equals(idWorkers, that.idWorkers) && Objects.equals(bank, that.bank) && Objects.equals(cash, that.cash) && Objects.equals(taxes, that.taxes) && Objects.equals(bold, that.bold) && Objects.equals(indebtedness, that.indebtedness) && Objects.equals(total, that.total) && Objects.equals(date, that.date) && Objects.equals(totalBuy, that.totalBuy) && Objects.equals(utilities, that.utilities) && Objects.equals(rUtilities, that.rUtilities) && Objects.equals(observations, that.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInvoice, idBill, idCustomer, idCompany, idPartners, idProviders, idWorkers, bank, cash, taxes, bold, indebtedness, total, date, totalBuy, utilities, rUtilities, observations);
    }

    public CompanyClass getCompanyByIdCompany() {
        return companyByIdCompany;
    }

    public void setCompanyByIdCompany(CompanyClass companyByIdCompany) {
        this.companyByIdCompany = companyByIdCompany;
    }

    public CustomerClass getCustomerByIdCustomer() {
        return customerByIdCustomer;
    }

    public void setCustomerByIdCustomer(CustomerClass customerByIdCustomer) {
        this.customerByIdCustomer = customerByIdCustomer;
    }

    public PartnersClass getPartnersByIdPartners() {
        return partnersByIdPartners;
    }

    public void setPartnersByIdPartners(PartnersClass partnersByIdPartners) {
        this.partnersByIdPartners = partnersByIdPartners;
    }

    public ProvidersClass getProvidersByIdProviders() {
        return providersByIdProviders;
    }

    public void setProvidersByIdProviders(ProvidersClass providersByIdProviders) {
        this.providersByIdProviders = providersByIdProviders;
    }
    public WorkersClass getWorkersByIdWorkers() {
        return workersByIdWorkers;
    }
    public void setWorkersByIdWorkers(WorkersClass workersByIdWorkers) {
        this.workersByIdWorkers = workersByIdWorkers;
    }
    public Collection<WareinvoiceClass> getWareinvoicesByIdInvoice() {
        return wareinvoicesByIdInvoice;
    }
    public void setWareinvoicesByIdInvoice(Collection<WareinvoiceClass> wareinvoicesByIdInvoice) {
        this.wareinvoicesByIdInvoice = wareinvoicesByIdInvoice;
    }
    public Collection<MoveinvoiceClass> getMoveinvoicesByIdInvoice() {
        return moveinvoicesByIdInvoice;
    }

    public void setMoveinvoicesByIdInvoice(Collection<MoveinvoiceClass> moveinvoicesByIdInvoice) {
        this.moveinvoicesByIdInvoice = moveinvoicesByIdInvoice;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getBold() {
        return bold;
    }

    public void setBold(String bold) {
        this.bold = bold;
    }
}
