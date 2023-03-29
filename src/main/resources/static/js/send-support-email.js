function showEmailSuggestions() {
    var input = document.getElementById("email");
    var email = input.value;
    var url = "mail/suggest-emails?email=" + email;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var emailSuggestions = JSON.parse(xhr.responseText);
                var emailSuggestionsDiv = document.getElementById("email-suggestions");
                emailSuggestionsDiv.innerHTML = "";
                if (emailSuggestions.length > 0) {
                    var list = document.createElement("ul");
                    emailSuggestions.forEach(function(email) {
                        var item = document.createElement("li");
                        item.innerHTML = email;
                        item.onclick = function() {
                            input.value = email;
                            emailSuggestionsDiv.innerHTML = "";
                        };
                        list.appendChild(item);
                    });
                    emailSuggestionsDiv.appendChild(list);
                }
            }
        }
    };
    xhr.send();
}
