package com.example.accountspringdatajpa.specification;
//lớp này sẽ kiểm tra các kiểu dữ liệu trường, loại so sánh, giá trị cần so sánh

public class SearchCriteria {
    private String key; // Trường cần so sanh ví dụ là Id
    private SearchCriteriaOperator operator; // Toán tử so sánh >,<, =
    private Object value; // Giá trị cần so sánh
    // Vd: chung Id = 1; Id là key, = là operation, 1 là value


    public SearchCriteria() {
    }

    public SearchCriteria(final String key, final SearchCriteriaOperator operator, final Object value) {
        super();
        this.key = key;
        this.operator = operator;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SearchCriteriaOperator getOperator() {
        return operator;
    }

    public void setOperator(SearchCriteriaOperator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
