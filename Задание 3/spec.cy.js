describe('template spec', () => {
  beforeEach(() => {
    cy.visit('https://www.saucedemo.com')
    cy.intercept('POST', 'https://events.backtrace.io/**', { statusCode: 200 }).as('backtrace');
  })
  it('passes', () => {
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();
  });

  it('filter', () => {
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();

    let lastPrice = 0.0;

    cy.get('.product_sort_container').children('option').should('have.length', 4).eq(2).should('have.value', 'lohi');

    cy.get('.product_sort_container').select('lohi');

    cy.get('.inventory_item[data-test="inventory-item"]').each((el) => {
      const priceText = el.find('.inventory_item_price[data-test="inventory-item-price"]').text();
      const price = parseFloat(priceText.replace('$', ''));

      cy.log(`Price: ${price}`);
      cy.log(`Last Price: ${lastPrice}`);

      if (price !== lastPrice) {
        expect(price).to.be.greaterThan(lastPrice);
      }

      lastPrice = price;
    });

    cy.get('.product_sort_container').children('option').should('have.length', 4).eq(3).should('have.value', 'hilo');

    cy.get('.product_sort_container').select('hilo');

    cy.get('.inventory_item[data-test="inventory-item"]').each((el) => {
      const priceText = el.find('.inventory_item_price[data-test="inventory-item-price"]').text();
      const price = parseFloat(priceText.replace('$', ''));

      cy.log(`Price: ${price}`);
      cy.log(`Last Price: ${lastPrice}`);

      if (price !== lastPrice) {
        expect(price).to.be.lessThan(lastPrice);
      }

      lastPrice = price;
    });
  });

  it('order', () => {
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();

    cy.get('#add-to-cart-sauce-labs-backpack').click();
    cy.get('#add-to-cart-sauce-labs-bike-light').click();
    cy.get('.shopping_cart_link').click();
    cy.get('#checkout').click();

    cy.get('#first-name').type('Никита');
    cy.get('#last-name').type('Цупрун');
    cy.get('#postal-code').type('426000');
    cy.get('#continue').click();
    cy.get('#finish').click();

    cy.get('#back-to-products').click();
  });
})