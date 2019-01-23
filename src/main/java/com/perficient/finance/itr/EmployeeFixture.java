package com.perficient.finance.itr;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.util.List;
import static com.perficient.finance.itr.CsvUtils.readFromCsv;

@Component
public class EmployeeFixture {
    private final ObjectReader objectReader;

    public EmployeeFixture() {
        CsvSchema schema = CsvSchema.builder()
                .addColumn("empId")
                .addColumn("empName")
                .addColumn("email")
                .build();

        objectReader = new CsvMapper().readerFor(Employee.class).with(schema);
    }

    public List<Employee> load() {
        return readFromCsv(objectReader, "employeelist.csv");
    }
}
