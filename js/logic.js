// Example Java variables
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








// Define the politician's name
var politicianName = "Steven Guilbeault"; // Example name
document.getElementById('politician').textContent = politicianName;

// Define the base rates
var indigenousRightsRate = 85; // Example percentage
var lgbtqRate = 85; // Example percentage
var immigrationRate = 70; // Example percentage
var fiscalRate = 75; // Example percentage
var housingAffordability = 70
var healthcare = 36
var dataPrivacy = 50

// Calculate minimum and maximum multipliers
const minMultiplier = 50 / 70;
const maxMultiplier = 80 / 70;

// Generate a random multiplier within the range
const randomMultiplier = Math.random() * (maxMultiplier - minMultiplier) + minMultiplier;

// Apply the randomMultiplier to adjust each rate
indigenousRightsRate *= randomMultiplier;
lgbtqRate *= randomMultiplier;
immigrationRate *= randomMultiplier;
fiscalRate *= randomMultiplier;
housingAffordability *= randomMultiplier;
healthcare *= randomMultiplier;
dataPrivacy *= randomMultiplier;

// Calculate the totalAgreementRate as an average of the adjusted rates
var totalAgreementRate = (indigenousRightsRate + lgbtqRate + immigrationRate + fiscalRate + housingAffordability + healthcare + dataPrivacy) / 7;

// Update the HTML elements with the adjusted rates and totalAgreementRate
document.getElementById('totalagreementrate').textContent = totalAgreementRate.toFixed(2) + '%'; // Use toFixed(2) for precision
document.getElementById('indigenousrightsrate').textContent = indigenousRightsRate.toFixed(2) + '%';
document.getElementById('lgbtqrate').textContent = lgbtqRate.toFixed(2) + '%';
document.getElementById('immigrationrate').textContent = immigrationRate.toFixed(2) + '%';
document.getElementById('fiscalrate').textContent = fiscalRate.toFixed(2) + '%';
document.getElementById('housing').textContent = housingAffordability.toFixed(2) + '%';
document.getElementById('healthcare').textContent = healthcare.toFixed(2) + '%';
document.getElementById('dataprivacy').textContent = dataPrivacy.toFixed(2) + '%';
