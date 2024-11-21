document.getElementById('message').addEventListener('input', function (e) {
    const maxLength = 1000;
    const currentLength = e.target.value.length;
    const remaining = maxLength - currentLength;
    document.getElementById('remaining-chars').textContent = `Hátralévő karakterek: ${remaining}`;
});
