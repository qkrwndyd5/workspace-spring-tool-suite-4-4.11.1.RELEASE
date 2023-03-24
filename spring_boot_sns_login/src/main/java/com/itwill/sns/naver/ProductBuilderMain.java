package com.itwill.sns.naver;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductBuilderMain {

	public static void main(String[] args) {
		Product product1 = new Product(1,"TY", BigInteger.valueOf(456121345));
		
		Product product2 = Product.builder().no(1).name("COMPUTER")
						   .price(BigInteger.valueOf(2121212178)).build();

	}

}
