document.addEventListener('DOMContentLoaded', () => {
    const surveyResultsElement = document.getElementById('surveyResults');
    const surveyData = JSON.parse(localStorage.getItem('surveyData'));

    if (surveyData && surveyResultsElement) {
        const resultsHtml = Object.entries(surveyData).map(([key, value]) => {
            // Create a string for each key-value pair
            return `<p><strong>${key}:</strong> ${value}</p>`;
        }).join(''); // Join all strings into a single HTML string

        surveyResultsElement.innerHTML = resultsHtml; // Insert the results HTML into the page
    } else {
        surveyResultsElement.innerHTML = '<p>No survey data found.</p>';
    }
});
