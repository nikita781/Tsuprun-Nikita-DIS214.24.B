describe('template spec', () => {
  beforeEach(() => {
    cy.visit('https://www.tsum.ru/');
    cy.intercept('POST', 'https://events.backtrace.io/**', { statusCode: 200 }).as('backtrace');
  })

  it('cy.title() - get the title', () => {
    // https://on.cypress.io/title
    cy.title().should('include', 'Интернет-магазин ЦУМ: одежда, обувь и аксессуары ведущих мировых брендов')
  });

  it('cy.getCookies() - get browser cookies for the current domain', () => {
    cy.getCookies().should('not.be.empty').should((cookies) => {
      // cy.getCookies() yields an array of cookies
      const phoneCookie = cookies.find(cookie => cookie.name === '_calltracking')
      expect(phoneCookie).to.have.property('value', '+7 800 500 80 00,+7 495 933 73 00')
    })
  })

  it('Загружается главная страница и отображаются главные блоки', () => {
    cy.contains('Новые поступления')
        .should('be.visible');
    cy.get('.Slide__webPageThemeBanner___vmwKA')
        .should('have.length.greaterThan', 0);

    cy.contains('Что носить прямо сейчас')
        .should('be.visible');
    cy.get('.Slide__webPageBanner___grVES ')
        .should('have.length.greaterThan', 0);

    cy.contains('Подарочные карты')
        .should('be.visible');
    cy.get('.Banners__banner___pyZVP')
        .should('have.length.greaterThan', 0);
  });

  it('Фильтрация по категории товаров', () => {
    cy.get('.MobileNavigation__item___U0wUa')
        .find('.MobileNavigation__link___ojZhA')
        .find('p')
        .contains('Каталог')
        .click();

    cy.get('.CategoryBanners__categoryTitle___xe2Qy').contains('ОДЕЖДА').click();
    cy.get('.Mobile__subCategory___S8Sxg')
        .find('.Mobile__subCategoryTitle___w23yE')
        .contains('Худи')
        .click();

    cy.get('.ProductList__list___WnjEh')
        .each(($el) => {
          cy.wrap($el).find('.InternalProductCard__container___IPYI4')
              .find('.Body__body___SzCKz')
              .find('.Top__bodyTopLink___fW7Oi')
              .find('.Title__title___ujUQX')
              .should(($title) => {
                expect($title.text()).to.include('Худи');
              });
        });
  });

  it('Поиск товара по названию', () => {
    cy.get('.DefaultHeader__search___c3zEF')
        .find('.style_paragraph__0f1c562f')
        .contains('Поиск по каталогу')
        .click();

    cy.get('.SearchDialog__mobileHeader___YK9ee')
        .find('.style_container__e0b60c2f')
        .find('input')
        .type('Худи{enter}');

    cy.get('.ProductList__list___WnjEh')
        .each(($el) => {
          cy.wrap($el).find('.InternalProductCard__container___IPYI4')
              .find('.Body__body___SzCKz')
              .find('.Top__bodyTopLink___fW7Oi')
              .find('.Title__title___ujUQX')
              .should(($title) => {
                expect($title.text()).to.include('Худи');
              });
        });
  });

  it('Добавление товара в корзину', () => {
    cy.get('.MobileNavigation__item___U0wUa')
        .find('.MobileNavigation__link___ojZhA')
        .find('p')
        .contains('Каталог')
        .click();

    cy.get('.CategoryBanners__categoryTitle___xe2Qy').contains('ОДЕЖДА').click();
    cy.get('.Mobile__subCategory___S8Sxg')
        .find('.Mobile__subCategoryTitle___w23yE')
        .contains('Худи')
        .click();

    cy.get('.ProductList__list___WnjEh')
        .find('.InternalProductCard__container___IPYI4')
        .find('.Body__body___SzCKz')
        .find('.Top__bodyTopLink___fW7Oi')
        .find('.Title__title___ujUQX')
        .contains('Хлопковое худи')
        .click();

    cy.get('.Controlls__buttonsWrapper___vQs5V')
        .find('button')
        .contains('Добавить в корзину')
        .click();

    cy.get('.content__cartButton___qU3lG ')
        .click();

    cy.get('.ProductsList__cards___UdSXL')
        .should('have.length.greaterThan', 0);
  });

  it('Оформление заказа после добавления товара в корзину', () => {
    cy.get('.MobileNavigation__item___U0wUa')
        .find('.MobileNavigation__link___ojZhA')
        .find('p')
        .contains('Каталог')
        .click();

    cy.get('.CategoryBanners__categoryTitle___xe2Qy').contains('ОДЕЖДА').click();
    cy.get('.Mobile__subCategory___S8Sxg')
        .find('.Mobile__subCategoryTitle___w23yE')
        .contains('Худи')
        .click();

    cy.get('.ProductList__list___WnjEh')
        .find('.InternalProductCard__container___IPYI4')
        .find('.Body__body___SzCKz')
        .find('.Top__bodyTopLink___fW7Oi')
        .find('.Title__title___ujUQX')
        .contains('Хлопковое худи')
        .click();

    cy.get('.Controlls__buttonsWrapper___vQs5V')
        .find('button')
        .contains('Добавить в корзину')
        .click();

    cy.get('.content__cartButton___qU3lG ')
        .click();

    cy.get('.ProductsList__cards___UdSXL')
        .should('have.length.greaterThan', 0);

    cy.get('.Summary__cta___dBJy7')
        .click();

    cy.url().should('include', '/checkout');

    cy.get('.SegmentsPicker__picker___buq2K')
        .find('[for="strategy-segment-1"]')
        .click();

    cy.get('.Controls__sbpPayButton___sixsD')
        .click();

    cy.get('.contacts__phoneInput___wPVob')
        .find('.style_input__502ff58e')
        .type('1111111111');

    cy.get('.Approve__modalPayBtn___E7Kqm')
        .click();
  });
})