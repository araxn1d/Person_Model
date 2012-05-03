/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.modules.profile.models;

import addressbook.infrastructure.interfaces.IAssignable;
import addressbook.infrastructure.interfaces.ICloneable;
import addressbook.infrastructure.interfaces.INullable;
import addressbook.infrastructure.interfaces.IValidator;
import addressbook.mocks.profile.AddressMock;
import addressbook.mocks.profile.PersonMock;
import addressbook.mocks.profile.PhoneMock;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jeka
 */
public class ProductModel implements INullable, ICloneable, IAssignable, IValidator {

    /**
     * Fabric method, that create new Model instance
     *
     * @param person - not null
     * @param adress - not null
     * @param phone - not null
     * @return new Model object
     */
    public static ProductModel GetInstance(PersonMock person, AddressMock adress, PhoneMock phone)
            throws IllegalArgumentException {
        if (phone == null || adress == null || person == null) {
            throw new IllegalArgumentException("Parameters must be not null");
        }
        return new ProductModel(person, adress, phone);
    }

    /**
     *
     * @return new Model object with nullable content
     */
    public static ProductModel GetNullableInstance() {
        ProductModel person = new ProductModel(PersonMock.getNullableInstance(),
                AddressMock.GetNullableInstance(), PhoneMock.getNullableInstance());
        person.m_isNull = true;
        return person;
    }

    /**
     * Private empty constructor. Create new Model object with nullable content.
     *
     */
    private ProductModel() {
        this(new PersonMock(), new AddressMock(), new PhoneMock());
    }

    /**
     * Private constructor. Copied mocks and stored in Model object.
     *
     * @param person - not null
     * @param adress - not null
     * @param phone - not null
     */
    private ProductModel(PersonMock person, AddressMock adress, PhoneMock phone) {
        boolean a = person.AssignTo(this.m_person);
        boolean b = adress.AssignTo(this.m_adress);
        boolean c = phone.AssignTo(this.m_phone);
        if (!a || !b || !c) {
            m_isNull = true;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see addressbook.infrastructure.interfaces.ICloneable#Clone()
     */
    @Override
    public Object Clone() {
        ProductModel person = new ProductModel();
        AssignTo(person);
        return person;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * addressbook.infrastructure.interfaces.IAssignable#AssignTo(java.lang.Object)
     */
    @Override
    public boolean AssignTo(Object obj) {
        if (obj != null && obj instanceof ProductModel) {
            ProductModel person = (ProductModel) obj;

            m_adress.AssignTo(person.m_adress);
            m_phone.AssignTo(person.m_phone);
            m_person.AssignTo(person.m_person);

            return true;
        }

        return false;
    }

    /**
     * (non-Javadoc)
     *
     * @see addressbook.infrastructure.interfaces.INullable#IsNull()
     */
    @Override
    public boolean IsNull() {
        return m_isNull;
    }

    /**
     *
     * @return true if object is valid (has valid fields) else return false
     */
    @Override
    public boolean Validate() {
        //Create patterns to validate fields of object
        Pattern name = Pattern.compile(nameValidation);
        Pattern date = Pattern.compile(birthDateValidtation);
        Pattern email = Pattern.compile(eMailValidtation);
        Pattern phone = Pattern.compile(phoneValidation);
        //Create mathcers to match fieilds with patterns
        Matcher fnameMatch = name.matcher(this.GetFirstName());
        Matcher lnameMatch = name.matcher(this.GetLastName());
        Matcher dateMatch = date.matcher(this.GetDate());
        Matcher emailMatch = email.matcher(this.GetEMail());
        Matcher phoneMatch = phone.matcher(this.GetPhone());
        //define if the fields are valid
        boolean ans = fnameMatch.matches() || lnameMatch.matches() || dateMatch.matches()
                || emailMatch.matches() || phoneMatch.matches();

        return ans;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductModel other = (ProductModel) obj;
        if (!Objects.equals(this.m_adress, other.m_adress)) {
            return false;
        }
        if (!Objects.equals(this.m_phone, other.m_phone)) {
            return false;
        }
        if (!Objects.equals(this.m_person, other.m_person)) {
            return false;
        }
        if (this.m_isNull != other.m_isNull) {
            return false;
        }
        return true;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.m_adress);
        hash = 79 * hash + Objects.hashCode(this.m_phone);
        hash = 79 * hash + Objects.hashCode(this.m_person);
        hash = 79 * hash + (this.m_isNull ? 1 : 0);
        return hash;
    }

    public void SetID(int id) {
        m_person.SetId(id);
        m_adress.SetPersonId(id);
        m_phone.SetPersonId(id);
    }

    public Integer GetId() {
        return m_person.GetId();
    }

    public void SetFirstName(String firstName) {
        if (null != firstName) {
            m_person.SetFirstName(firstName);
        }
    }

    public String GetFirstName() {
        return m_person.GetFirstName();
    }

    public void SetLastName(String lastName) {
        if (null != lastName) {
            m_person.SetLastName(lastName);
        }
    }

    public String GetLastName() {
        return m_person.GetLastName();
    }

    public void SetDate(String date) {
        if (null != date) {
            m_person.SetBirthDate(date);
        }
    }

    public String GetDate() {
        return m_person.GetBirthDate();
    }

    public void SetPhone(String phone) {
        if (null != phone) {
            m_phone.SetPhone(phone);
            m_phone.SetPersonId(m_person.GetId());
        }
    }

    public String GetPhone() {
        return m_phone.GetPhone();
    }

    public void SetAddress(String address) {
        if (null != address) {
            m_adress.SetAddress(address);
            m_adress.SetPersonId(m_person.GetId());
        }
    }

    public String GetAdress() {
        return m_adress.GetAdress();
    }

    public void SetEMail(String mail) {
        if (null != mail) {
            m_person.SetEMail(mail);
        }
    }

    public String GetEMail() {
        return m_person.GetEMail();
    }
    //Patterns which should validate fields, such as FirstName, Phone and etc.
    private String phoneValidation = "8-\\d{3}-\\d{2}-\\d{3}-\\d{2}";
    private String eMailValidtation = "[a-z0-9]{1,14}@[a-z0-9]{1,6}\u002E[a-z]{1,3}";
    private String birthDateValidtation = "19\\d\\d\u002E[0-1][0-9]\u002E[0-3][0-9]";
    private String nameValidation = "[A-Z]{1}[a-z]{3,20}";
    protected AddressMock m_adress = null;
    protected PhoneMock m_phone = null;
    protected PersonMock m_person = null;
    protected boolean m_isNull = false;
}
