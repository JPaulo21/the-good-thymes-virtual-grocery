import { routes } from "./routes.js";

async function addComment(comment){
    const response = await fetch(`${routes.comment}`, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(comment)
    }).catch(erro => {
        console.error('Error adicionar comentário', erro);
    });

   return response.ok;
}

export const commentService = {
    addComment
}