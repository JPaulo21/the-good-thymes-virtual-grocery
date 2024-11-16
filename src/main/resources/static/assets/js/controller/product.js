import { orderlineService } from "../service/orderlineService.js";
import { headerService } from "../common/header.js";
import { notificationService } from "../utils/notifications.js";

const product = document.getElementById('product');

console.log(product)

const productComponent = {
    productId: product.getAttribute("data-product-id"),
    btnDecrement: product.querySelector('.decrement'),
    btnIncrement: product.querySelector('.increment'),
    quantityProduct: product.querySelector('.quantity-product'),
    btnAddCart: product.querySelector('.add-to-cart'),
    btnBuyNow: product.querySelector('.buy-now')
}

function orderlineData(productComponent){
    return {
        productId: productComponent.productId,
        quantity: productComponent.quantityProduct.value
    }
}

productComponent.btnDecrement.addEventListener('click', () => {
    productComponent.quantityProduct.value--;            
});

productComponent.btnIncrement.addEventListener('click', () => {
    productComponent.quantityProduct.value++;           
});

productComponent.btnAddCart.addEventListener('click', async () => {
    const orderline = orderlineData(productComponent);
    const isOk = await orderlineService.addOrderline(orderline);

    if(isOk){
        await headerService.updateCart();
        document.body.insertAdjacentHTML('beforeend', notificationService.sucess("Produto adicionado ao carrinho!"));
        productComponent.quantityProduct.value = 1;
    }
});

productComponent.btnBuyNow.addEventListener('click', async () => {
    const orderline = orderlineData(productComponent);

    //await orderlineService


    await orderlineService.addOrderline(orderline).then((isOk) => {
        if(isOk)
            window.location.href = '/cart';
    });
});

// ---------------------------------

