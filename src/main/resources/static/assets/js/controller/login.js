import { notificationService } from "../utils/notifications.js";
import { customerService } from "../service/customerService.js";

const formLogin = document.querySelector('[data-form-login]');

formLogin.addEventListener('submit', async (event) => {
    event.preventDefault();
    const formDataUser = new FormData(formLogin);
    const loginData = Object.fromEntries(formDataUser);

    const response = await customerService.login(loginData)
        .catch((error) => {
            document.body.insertAdjacentHTML('beforeend', notificationService.error("Usuário/Senha inválidos!"));
        });

    if(response.status)
        window.location.href=response.url;

});