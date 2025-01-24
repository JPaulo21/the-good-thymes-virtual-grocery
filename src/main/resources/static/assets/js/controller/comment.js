import { commentService } from "../service/commentService.js";

const formComment = document.querySelector("[data-form-comment]");
const product = document.getElementById('product');

formComment.addEventListener('submit', async (event) => {
    event.preventDefault();

    const dataFormComment = new FormData(formComment);
    const dataComment = Object.fromEntries(dataFormComment);
    dataComment.email = JSON.parse(localStorage.getItem('customer')).email;
    dataComment.productId = product.getAttribute("data-product-id"),

    commentService.addComment(dataComment);
});