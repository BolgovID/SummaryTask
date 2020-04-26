function changeLanguage() {
    var form = document.getElementById('lang');
    form.submit();
}

function dateFrom(element) {
    var newData = new Date(element.value);
    newData.setDate(newData.getDate() + 1);
    var toDate = document.getElementById('toDate');
    toDate.valueAsDate = newData;
    toDate.min = newData.toISOString().split("T")[0];
    toDate.disabled = false;
}

function toggleInput(element, text, arrayIdElements) {
    arrayIdElements.forEach(id => {
        var textArea = document.getElementById(id);
        textArea.disabled = element.value === text;
        if (textArea.disabled) {
            textArea.style.opacity = '0.6';
            textArea.value = '';
        } else
            textArea.style.opacity = '1';
    });
}

