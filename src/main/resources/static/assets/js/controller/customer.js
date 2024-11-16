import { customerService } from "../service/customerService.js";
import { notificationService } from "../utils/notifications.js";

const formCustomer = document.querySelector("[data-form-customer]");

console.log(formCustomer);


formCustomer.addEventListener('submit', async (event) => {
    event.preventDefault();

    const dataFormCustomer = new FormData(formCustomer);
    const dataCustomer = Object.fromEntries(dataFormCustomer);

    //console.log(dataCustomer);

    await customerService.create(dataCustomer)
        .then((response) => {
            if(response){
                console.log(`USUARIO SALVO COM SUCESSO!`)
                window.location.href= `/`;
            }       
        })
        .catch((error) => {
            document.body.insertAdjacentHTML('beforeend',notificationService.error(error.message));
        });
})