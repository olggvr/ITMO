document.addEventListener("DOMContentLoaded", function () {
    let clock = document.querySelector('.clock');

    function time() {
        let date = new Date();

        let data = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
        let month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
        let year = date.getFullYear();
        let hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours();
        let minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();
        let seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();

        clock.innerHTML = `${data}.${month}.${year}<br>${hours}:${minutes}:${seconds}`;
    }

    setInterval(time, 13000);
    time();
});
