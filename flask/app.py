from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

@app.route('/', methods=[  'POST'])
def datos():

    data = request.get_json()

    selectedAction = data.get('selectedAction')
    startDate = data.get('startDate')
    startHour = data.get('startHour')
    hasResponsible = data.get('hasResponsible')
    hasObjective = data.get('hasObjective')
    footprint = data.get('footprint')
    legalObligation = data.get('legalObligation')

    mostrar_datos = {
        "selectedAction": selectedAction,
        "startDate": startDate,
        "startHour": startHour,
        "hasResponsible": hasResponsible,
        "hasObjective": hasObjective,
        "footprint": footprint,
        "legalObligation": legalObligation,
    }
    print(mostrar_datos)
    return jsonify({"datos": mostrar_datos})



if __name__ == '__main__':
    app.run(debug=True)