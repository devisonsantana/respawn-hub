class Game extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({ mode: "open" });
        shadow.appendChild(this.build());
        shadow.appendChild(this.style());
    }
    build() {
        // component root
        const componentRoot = document.createElement("div");
        componentRoot.setAttribute("class", "container");

        // card left
        /*
        const cardLeft = document.createElement("div");
        cardLeft.setAttribute("class", "card__left");
        // card right
        const cardRight = document.createElement("div");
        cardRight.setAttribute("class", "card__right");
        // into card right
        const cardRightInfo = document.createElement("div");
        cardRightInfo.setAttribute("class", "card__right__info");
        const cardRightRating = document.createElement("div");
        cardRightRating.setAttribute("class", "card__right__rating");
        const cardRightSite = document.createElement("div");
        cardRightSite.setAttribute("class", "card__right__site");
        const cardRightCategories = document.createElement("div");
        cardRightCategories.setAttribute("class", "card__right__categories");
        const cardRightPlatforms = document.createElement("div");
        cardRightPlatforms.setAttribute("class", "card__right__platforms");

        cardRight.appendChild(cardRightInfo);
        cardRight.appendChild(cardRightRating);
        cardRight.appendChild(cardRightSite);
        cardRight.appendChild(cardRightCategories);
        cardRight.appendChild(cardRightPlatforms);

        componentRoot.appendChild(cardLeft);
        componentRoot.appendChild(cardRight);
        */

        this.httpRequest().then(res => {
            res.map(game => {
                let template = `
                <div class="card">
                    <div class="card__left">
                        <img src="${game.backgroundImg}" alt="${game.slug}">
                    </div>
                    <div class="card__right">
                        <div class="card__right__info">
                            <h1>${game.title}</h1>
                            <span>Lançamento: ${this.convertDate(game.release)}</span>
                            <p>${game.descriptionRaw.substring(0, 200)}...</p>
                        </div>
                        <div class="card__right__rating">
                            <p>Rating Top: <span>${game.ratingTop}</span></p>
                            <p>Rating: <span>${game.rating}</span></p>
                        </div>
                        <div class="card__right__site">
                            <p>Website: <a href="${game.website}">${game.website}</a></p>
                        </div>
                        <div class="card__right__categories">
                            <p>Gênero: ${game.genres.map(g => ` <span>${g.name}</span>`)}</p>
                            <p>Tags: ${game.tags.map(t => ` <span>${t.name}</span>`)}</p>
                        </div>
                        <div class="card__right__platforms">
                            <p>Plataformas: ${game.platforms.map(p => ` <span>${p.name}</span>`)}</p>
                        </div>
                    </div>
                </div>
                `
                componentRoot.innerHTML += template;
            })
        })
        return componentRoot;
    }
    style() {
        const styles = document.createElement("style");
        styles.textContent = `
        .container {
            display: flex;
            flex-direction: column;
        }
        
        .card {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            max-width: 90%;
            padding: 5px;
            margin: 5px;
            border-radius: 20px;
            background-color: #464863;
        }
        
        .card__left {
            margin: 5px;
            background-color: inherit;
        }
        
        .card__left>img {
            width: 200px;
            height: auto;
            border-radius: 10px;
        }
        
        .card__left>img:hover {
            cursor: pointer;
            filter: brightness(90%);
        }
        
        .card__right {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            background-color: inherit;
        }
        
        .card__right__info {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        
        .card__right__info>span {
            font-size: 16px;
            color: #bec1ec;
        }
        
        .card__right__rating,
        .card__right__categories {
            display: flex;
            flex-direction: column;
            gap: 2px;
        }
        
        .card__right__rating p,
        .card__right__site p,
        .card__right__categories p,
        .card__right__platforms p {
            font-weight: 600;
        }
        
        .card__right__rating span:hover {
            cursor: pointer;
            color: #eeeb40;
        }
        
        .card__right__site>p>a {
            text-decoration: none;
            color: #e7876c;
            font-weight: 500;
        }
        
        .card__right__site>p>a:hover {
            cursor: pointer;
            text-decoration: underline;
        }
        
        .card__right__categories span,
        .card__right__platforms span {
            font-weight: 500;
            background-color: #4f537a;
        }
        
        .card__right__categories span:hover,
        .card__right__platforms span:hover {
            cursor: pointer;
            background-color: #2f3249;
        }
        `
        return styles;
    }
    convertDate(dateString) {
        var date = new Date(dateString);
        var dd = String(date.getDate()).padStart(2, '0');
        var mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = date.getFullYear();

        return dd + '/' + mm + '/' + yyyy;
    }
    httpRequest() {
        const url = "http://localhost:8080/api/rawg/game/entity";
        return fetch(url).then(res => res.json()).catch(err => {
            console.log("Erro ao acessar endpoint", err)
        });
    }
}

customElements.define("card-game", Game);