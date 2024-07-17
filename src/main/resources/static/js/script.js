
document.addEventListener("DOMContentLoaded", function () {
    let achatRadio = document.getElementById('achat');
    let venteRadio = document.getElementById('vente');
    let filter2AchatInputs = document.querySelectorAll('.achat');
    let filter2VenteInputs = document.querySelectorAll('.vente');

    function updateInputs() {
        if (achatRadio.checked) {
            filter2VenteInputs.forEach(function (input) {
                input.disabled = true;
            });
            filter2AchatInputs.forEach(function (input) {
                input.disabled = false;
            });
        } else if (venteRadio.checked) {
            filter2AchatInputs.forEach(function (input) {
                input.disabled = true;
            });
            filter2VenteInputs.forEach(function (input) {
                input.disabled = false;
            });
        } else {
            filter2VenteInputs.forEach(function (input) {
                input.disabled = true;
            });
            filter2AchatInputs.forEach(function (input) {
                input.disabled = true;
            });
        }
    }

    achatRadio.addEventListener('change', updateInputs);
    venteRadio.addEventListener('change', updateInputs);

    updateInputs(); // Call once to set initial state
});