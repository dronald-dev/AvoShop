package ua.dronald.avo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartModel {

    private List<ProductModel> products;

}
