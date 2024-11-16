import { orderlineService } from "../service/orderlineService.js";

async function updateCart(){
    const cart = await orderlineService.getCart();

    // Atualizando elementos do carrinho(cart) no header
    document.getElementById('quantity-products').textContent = cart.quantityProducts;
}

export const headerService = {
    updateCart
}