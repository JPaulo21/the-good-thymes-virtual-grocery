const SUCESS = 'sucess';
const ERROR = 'danger';

function sucess(message){
    setTimeout(() => {
        const toast = document.getElementById('toast-success');
        toast.remove();
    }, 4000);
    return componentSucess(message);
}

function error(message){
    setTimeout(() => {
        const toast = document.getElementById('toast-danger');
        toast.remove();
    }, 4000);
    return componentError(message);
}

function componentSucess(message){
    return `<div id="toast-success" class="absolute bottom-4 right-6 flex items-center p-4 mb-4 mr-1 text-sm text-green-800 border border-green-300 rounded-lg bg-green-50 dark:bg-gray-800 dark:text-green-400 dark:border-green-800" role="alert">
                <svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5Zm3.707 8.207-4 4a1 1 0 0 1-1.414 0l-2-2a1 1 0 0 1 1.414-1.414L9 10.586l3.293-3.293a1 1 0 0 1 1.414 1.414Z"/>
                </svg>
                <span class="sr-only">Info</span>
                <div id="message">
                    <span class="font-medium"></span> ${message}
                </div>
            </div>`;
}

function componentError(message){
    return `<div id="toast-danger" class="absolute bottom-4 right-6 flex items-center p-4 mb-4 text-sm text-red-800 border border-red-300 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400 dark:border-red-800" role="alert">
                <svg class="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5Zm3.707 11.793a1 1 0 1 1-1.414 1.414L10 11.414l-2.293 2.293a1 1 0 0 1-1.414-1.414L8.586 10 6.293 7.707a1 1 0 0 1 1.414-1.414L10 8.586l2.293-2.293a1 1 0 0 1 1.414 1.414L11.414 10l2.293 2.293Z"/>
                </svg>
                <span class="sr-only">Info</span>
                <div id="message">
                    <span class="font-medium"></span> ${message}
                </div>
            </div>`;
}

export const notificationService = {
    sucess,
    error
}