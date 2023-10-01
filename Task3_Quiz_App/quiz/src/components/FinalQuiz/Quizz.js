import React, { useState } from "react";
import "./Quizz.css";
import Clock from "../Clock/Clock";

export default function Quizz() {
  // Sample quiz questions and options
  const questions = [
    {
      id: 1,
      text: "What is the capital of France?",
      options: ["Paris", "London", "Berlin", "Madrid"],
      correctAnswer: "Paris",
    },
    {
      id: 2,
      text: "Which planet is known as the Red Planet?",
      options: ["Mars", "Venus", "Jupiter", "Saturn"],
      correctAnswer: "Mars",
    },
    {
      id: 3,
      text: "Which is not a programming language ?",
      options: ["Flutter", "Java", "English", "Kotlin"],
      correctAnswer: "English",
    },
  ];

  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [selectedOption, setSelectedOption] = useState("");
  const [score, setScore] = useState(0);
  const [quizCompleted, setQuizCompleted] = useState(false);

  const handleOptionChange = (e) => {
    setSelectedOption(e.target.value);
  };

  const handleNextQuestion = () => {
    if (selectedOption === questions[currentQuestionIndex].correctAnswer) {
      setScore(score + 1);
    }

    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
      setSelectedOption("");
    } else {
      setQuizCompleted(true);
    }
  };

  const handlePreviousQuestion = () => {
    if (currentQuestionIndex > 0) {
      setCurrentQuestionIndex(currentQuestionIndex - 1);
      setSelectedOption("");
    }
  };

  const handleMarkForReview = () => {};

  const handleClear = () => {
    setSelectedOption("");
  };

  const renderQuizContent = () => {
    return (
      <>
        <div className="header">
          <div className="Timer">
            Time Remaining : <strong>{<Clock />}</strong>
          </div>
        </div>
        <h1 style={{ textAlign: "center", margin: "3vh" }} className="title">
          Bharat Intern Quiz
        </h1>
        <div className="main">
          <div className="questions">
            Q : {questions[currentQuestionIndex].id}  
            {questions[currentQuestionIndex].text}
          </div>
          <div className="answers">
            {questions[currentQuestionIndex].options.map((option, index) => (
              <div className="options" key={index}>
                <input
                  type="radio"
                  id={`option-${index + 1}`}
                  value={option}
                  checked={selectedOption === option}
                  onChange={handleOptionChange}
                />
                <label htmlFor={`option-${index + 1}`}>{option}</label>
              </div>
            ))}
          </div>

          <div className="Buttons">
            <button onClick={handlePreviousQuestion}>Previous</button>
            <button onClick={handleNextQuestion}>Save & Next</button>
            <button onClick={handleMarkForReview}>Mark For Review</button>
            <button onClick={handleClear}>Clear</button>
          </div>
        </div>
      </>
    );
  };

  const renderScoreScreen = () => {
    return (
      <div className="score-container">
        <p className="head">    Thanks for      Attending       The quiz</p>
        <h1 className="scor">
          Your Score<br></br>         {score}/{questions.length}
        </h1>
      </div>
    );
  };

  return (
    <div className="Full">
      {quizCompleted ? renderScoreScreen() : renderQuizContent()}
    </div>
  );
}
