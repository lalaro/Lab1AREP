function mostrarFrase() {
    const mensaje = document.getElementById("mensajeUsuario").value;
    const frases = [
        "¡Nobitaaa! ¿Qué hiciste ahora? 😅",
        "Siempre hay un gadget para todo. 🚀",
        "Los amigos siempre están ahí para ayudar. ❤️",
        "¡Vamos a viajar en el tiempo! ⏳",
        "Confía en ti mismo, Nobita. 🌟"
    ];

    const fraseAleatoria = frases[Math.floor(Math.random() * frases.length)];

    document.getElementById("frase").textContent = `"${mensaje}" - Doraemon responde: ${fraseAleatoria}`;
}
