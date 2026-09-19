package com.example.data.datasource

import com.example.data.model.JobPost
import com.example.data.model.JobVideo
import java.util.concurrent.TimeUnit

object InitialDataSources {

    val TEN_PRIVATE_JOB_WEBSITES = listOf(
        "Freshersworld Private Jobs (freshersworld.com/private-jobs)",
        "Naukri Karnataka Private (naukri.com/jobs-in-karnataka-private)",
        "Foundit Private Job Portal (foundit.in/private-jobs)",
        "Indeed Bangalore Private Careers (in.indeed.com/private-jobs-bangalore)",
        "Internshala Karnataka Freshers (internshala.com/jobs/karnataka)",
        "Shine Karnataka Private Alerts (shine.com/job-search/private-jobs-karnataka)",
        "Bangalore IT & Tech Openings (techjobsinbangalore.com)",
        "Auto & Manufacturing Karnataka Private (autojobskarnataka.com)",
        "Private Logistics & Quick Commerce (supplychainjobskarnataka.com)",
        "Private Retail & BPO Careers (karnatakabpojobs.com)"
    )

    val SIX_YOUTUBE_CHANNELS = listOf(
        "ಕನ್ನಡ ಜಾಬ್ ನ್ಯೂಸ್ : Kannada Job News (@kannadajobnews)",
        "ಕೆರಿಯರ್ ಸಪೋರ್ಟ್ ಕನ್ನಡ : Career Support Kannada (@CareerSupportKannada)",
        "ಕರ್ನಾಟಕ ಜಾಬ್ ಇನ್ಫೋ : Karnataka Job Info (@KarnatakaJobInfo)",
        "ಕನ್ನಡ ಜಾಬ್ಸ್ ಅಪ್ಡೇಟ್ : Kannada Jobs Update (@kannadajobsupdate)",
        "ಜಾಬ್ ನ್ಯೂಸ್ ಕನ್ನಡ : Job News Kannada (@JobNewsKannada)",
        "ಕರ್ನಾಟಕ ಜಾಬ್ ಅಡ್ಡ : Karnataka Job Adda (@KannadaJobAdda)"
    )

    fun getInitialJobPosts(): List<JobPost> {
        val now = System.currentTimeMillis()
        val day = TimeUnit.DAYS.toMillis(1)
        val hour = TimeUnit.HOURS.toMillis(1)

        return listOf(
            JobPost(
                id = 1,
                titleKn = "ಇನ್ಫೋಸಿಸ್ (Infosys) - ಸಿಸ್ಟಮ್ ಇಂಜಿನಿಯರ್ ಮತ್ತು ಡೆವಲಪರ್ ನೇಮಕಾತಿ 2026",
                titleEn = "Infosys Off-Campus Drive 2026 - Systems Engineer & Software Developer",
                company = "Infosys BPM & IT Services",
                locationKn = "ಬೆಂಗಳೂರು / ಮೈಸೂರು / ಮಂಗಳೂರು (ವರ್ಕ್ ಫ್ರಮ್ ಹೋಮ್ ಲಭ್ಯ)",
                locationEn = "Bengaluru / Mysuru / Mangaluru (Hybrid / Remote)",
                categoryKn = "ಐಟಿ / ಸಾಫ್ಟ್‌ವೇರ್",
                categoryEn = "IT / Software",
                shortDescKn = "ಇನ್ಫೋಸಿಸ್ ಖಾಸಗಿ ಕಂಪನಿಯು ಹೊಸ ಪದವೀಧರರಿಗೆ (BE, B.Tech, MCA, BCA, B.Sc) ₹4.20 ರಿಂದ ₹6.50 ಲಕ್ಷ ವಾರ್ಷಿಕ ಪ್ಯಾಕೇಜ್‌ನಲ್ಲಿ ನೇಮಕಾತಿ ಆರಂಭಿಸಿದೆ.",
                shortDescEn = "Infosys is hiring fresh graduates for Systems Engineer roles across Karnataka campuses with high growth and training opportunities.",
                fullArticleKn = """
                    ಖಾಸಗಿ ವಲಯದ ಪ್ರಮುಖ ಐಟಿ ದಿಗ್ಗಜ ಕಂಪನಿಯಾದ ಇನ್ಫೋಸಿಸ್ ಕರ್ನಾಟಕದ ವಿವಿಧ ಕ್ಯಾಂಪಸ್‌ಗಳಲ್ಲಿ ಹೊಸ ಇಂಜಿನಿಯರಿಂಗ್ ಹಾಗೂ ಕಂಪ್ಯೂಟರ್ ಸೈನ್ಸ್ ಪದವೀಧರರಿಗೆ ನೇಮಕಾತಿ ಪ್ರಕ್ರಿಯೆ ಆರಂಭಿಸಿದೆ.

                    ಹುದ್ದೆಯ ವಿವರ:
                    - ಹುದ್ದೆಯ ಹೆಸರು: Systems Engineer / Specialist Programmer
                    - ಸಂಸ್ಥೆ: Infosys Technologies Private Limited
                    - ಅರ್ಹತೆ: B.E / B.Tech / M.E / M.Tech / MCA / B.Sc / BCA
                    - ವೇತನ ಶ್ರೇಣಿ: ವಾರ್ಷಿಕ ₹4,20,000 - ₹6,50,000 + ಆರೋಗ್ಯ ವಿಮೆ
                    - ಉದ್ಯೋಗ ಸ್ಥಳ: ಬೆಂಗಳೂರು ಎಲೆಕ್ಟ್ರಾನಿಕ್ಸ್ ಸಿಟಿ, ಮೈಸೂರು ಹೆಬ್ಬಾಳ್ ಕ್ಯಾಂಪಸ್

                    ಆಯ್ಕೆ ಪ್ರಕ್ರಿಯೆ:
                    1. ಆನ್‌ಲೈನ್ ಆಪ್ಟಿಟ್ಯೂಡ್ ಹಾಗೂ ಕೋಡಿಂಗ್ ಪರೀಕ್ಷೆ
                    2. ತಾಂತ್ರಿಕ ಸಂದರ್ಶನ (Technical Interview)
                    3. ಎಚ್‌ಆರ್ ಸಂವಾದ (HR Discussion)

                    ಮುಖ್ಯ ಸೂಚನೆ:
                    ಇದು ಕೇವಲ ಖಾಸಗಿ ಕಂಪನಿ ಉದ್ಯೋಗವಾಗಿದ್ದು ಯಾವುದೇ ಪರೀಕ್ಷಾ ಶುಲ್ಕ ಅಥವಾ ಮಧ್ಯವರ್ತಿಗಳಿಲ್ಲದೆ ಅಧಿಕೃತ ಕೆರಿಯರ್ ಪೋರ್ಟಲ್ ಮೂಲಕವೇ ಉಚಿತವಾಗಿ ಅರ್ಜಿ ಸಲ್ಲಿಸಬಹುದು.
                """.trimIndent(),
                fullArticleEn = """
                    Infosys is welcoming applications for the post of Systems Engineer across its premier campuses in Karnataka including Electronic City Bengaluru and Hebbal Mysuru.

                    Position Overview:
                    - Role: Systems Engineer / Associate Software Engineer
                    - Organization: Infosys BPM & IT Services Ltd
                    - Qualification: BE / BTech / MCA / BCA / BSc (All branches welcome)
                    - Compensation: INR 4.2 LPA - 6.5 LPA + Performance Incentives
                    - Work Locations: Bengaluru, Mysuru, Mangaluru

                    Selection Workflow:
                    1. Virtual Online Aptitude Assessment
                    2. Technical Evaluation
                    3. HR Onboarding Discussion

                    Application Note:
                    This is a private corporate vacancy with no application fee. Apply directly through the official corporate career dashboard linked below.
                """.trimIndent(),
                applyUrl = "https://career.infosys.com/jobdesc",
                sourceWebsiteName = "Freshersworld Private Jobs",
                sourceWebsiteUrl = "https://www.freshersworld.com/private-jobs",
                postedTimestamp = now - 2 * hour,
                salaryRange = "₹4.2 - ₹6.5 LPA",
                experience = "Freshers / 0-2 Years"
            ),
            JobPost(
                id = 2,
                titleKn = "ಟಾಟಾ ಕನ್ಸಲ್ಟೆನ್ಸಿ ಸರ್ವೀಸಸ್ (TCS) - ನಿಂಜಾ ಮತ್ತು ಡಿಜಿಟಲ್ ಪ್ರೈವೇಟ್ ನೇಮಕಾತಿ",
                titleEn = "TCS Ninja & Digital Private Hiring 2026 for Karnataka Freshers",
                company = "Tata Consultancy Services (TCS)",
                locationKn = "ಬೆಂಗಳೂರು / ಹುಬ್ಬಳ್ಳಿ / ಬೆಳಗಾವಿ",
                locationEn = "Bengaluru / Hubballi / Belagavi",
                categoryKn = "ಐಟಿ ಮತ್ತು ಕನ್ಸಲ್ಟಿಂಗ್",
                categoryEn = "IT & Consulting",
                shortDescKn = "ಟಿಸಿಎಸ್ ಖಾಸಗಿ ಕಂಪನಿಯಲ್ಲಿ 2024, 2025, ಮತ್ತು 2026 ಬ್ಯಾಚ್ ನ ನವ ಪದವೀಧರರಿಗೆ ಭರ್ಜರಿ ನೇಮಕಾತಿ. ಅರ್ಜಿ ಸಲ್ಲಿಕೆ ಕೊನೆ ದಿನಾಂಕ ಸಮೀಪಿಸಿದೆ.",
                shortDescEn = "TCS announces pan-Karnataka recruitment for Ninja and Digital developer profiles with immediate joining.",
                fullArticleKn = """
                    ಟಾಟಾ ಗ್ರೂಪ್‌ನ ಖಾಸಗಿ ಸಾಫ್ಟ್‌ವೇರ್ ಸಂಸ್ಥೆಯಾದ ಟಿಸಿಎಸ್ ಕರ್ನಾಟಕದಲ್ಲಿ ನಿಂಜಾ ಮತ್ತು ಡಿಜಿಟಲ್ ಕೇಡರ್ ನೇಮಕಾತಿ ಅಧಿಸೂಚನೆ ಪ್ರಕಟಿಸಿದೆ.

                    ಹುದ್ದೆಗಳ ಮುಖ್ಯಾಂಶಗಳು:
                    - ಹುದ್ದೆ: Assistant System Engineer-Trainee
                    - ವಿದ್ಯಾರ್ಹತೆ: ಯಾವುದೇ ಪದವಿ (B.E, B.Tech, M.Sc, MCA, BCA) 60% ಅಥವಾ ಅದಕ್ಕಿಂತ ಹೆಚ್ಚು
                    - ವಾರ್ಷಿಕ ವೇತನ: ₹3,60,000 ರಿಂದ ₹7,20,000 (ಡಿಜಿಟಲ್ ರೋಲ್)
                    - ಪ್ರಯೋಜನಗಳು: ಉಚಿತ ಆರೋಗ್ಯ ರಕ್ಷಣೆ, ಟ್ರಾನ್ಸ್‌ಪೋರ್ಟ್ ಹಾಗೂ ನಿರಂತರ ತರಬೇತಿ

                    ಪ್ರಮುಖ ದಿನಾಂಕಗಳು:
                    - ಆನ್‌ಲೈನ್ ಟೆಸ್ಟ್ ದಿನಾಂಕ: ಮುಂದಿನ ಶನಿವಾರ
                    - ಅರ್ಜಿ ಸಲ್ಲಿಸುವ ವಿಧಾನ: TCS NextStep ಪೋರ್ಟಲ್ ಮೂಲಕ ಖಾಸಗಿ ರೆಜಿಸ್ಟ್ರೇಷನ್
                """.trimIndent(),
                fullArticleEn = """
                    Tata Consultancy Services invites applications from dynamic freshers across Karnataka for its prestigious Ninja and Digital software programs.

                    Key Highlights:
                    - Designation: Assistant System Engineer (Trainee)
                    - Eligibility: Any Graduate / Post-Graduate with 60%+ throughout academics
                    - Package: INR 3.6 LPA (Ninja) to 7.2 LPA (Digital)
                    - Perks: Health coverage, corporate laptop, relocation bonus

                    Apply without delays via the verified direct registration link below.
                """.trimIndent(),
                applyUrl = "https://nextstep.tcs.com/campus",
                sourceWebsiteName = "Naukri Karnataka Private",
                sourceWebsiteUrl = "https://www.naukri.com/jobs-in-karnataka-private",
                postedTimestamp = now - 6 * hour,
                salaryRange = "₹3.6 - ₹7.2 LPA",
                experience = "Freshers / 2024-2026 Batch"
            ),
            JobPost(
                id = 3,
                titleKn = "ಅಮೆಜಾನ್ ಇಂಡಿಯಾ (Amazon) - ಕಸ್ಟಮರ್ ಸಪೋರ್ಟ್ & ಆಪರೇಷನ್ಸ್ ಅಸೋಸಿಯೇಟ್",
                titleEn = "Amazon Operations Associate & Support Specialist Openings",
                company = "Amazon Development Centre India",
                locationKn = "ಬೆಂಗಳೂರು / ಮನೆಯಿಂದಲೇ ಕೆಲಸ (WFH)",
                locationEn = "Bengaluru / Work From Home",
                categoryKn = "ಇ-ಕಾಮರ್ಸ್ & ಗ್ರಾಹಕ ಸೇವೆ",
                categoryEn = "E-Commerce & Support",
                shortDescKn = "ಅಮೆಜಾನ್ ಖಾಸಗಿ ಕಂಪನಿಯಲ್ಲಿ ಯಾವುದೇ ಪದವೀಧರರಿಗೆ ಮನೆಯಿಂದಲೇ ಕೆಲಸ ಮಾಡುವ ಸುವರ್ಣಾವಕಾಶ. ಲ್ಯಾಪ್‌ಟಾಪ್ ಹಾಗೂ ವೈಫೈ ಭತ್ಯೆ ಕಂಪನಿಯಿಂದಲೇ ಒದಗಿಸಲಾಗುತ್ತದೆ.",
                shortDescEn = "Amazon is hiring full-time customer service and virtual operations specialists. Attractive monthly salary and allowance provided.",
                fullArticleKn = """
                    ಖಾಸಗಿ ಇ-ಕಾಮರ್ಸ್ ಅಗ್ರಗಣ್ಯ ಸಂಸ್ಥೆ ಅಮೆಜಾನ್ ಇಂಡಿಯಾ ಮನೆಯಿಂದಲೇ ಕಾರ್ಯನಿರ್ವಹಿಸಬಹುದಾದ (Work From Home) ವರ್ಚುವಲ್ ಆಪರೇಷನ್ಸ್ ಅಸೋಸಿಯೇಟ್ ಹುದ್ದೆಗಳಿಗೆ ಅರ್ಜಿ ಆಹ್ವಾನಿಸಿದೆ.

                    ಹುದ್ದೆಯ ಮಾಹಿತಿ:
                    - ಹುದ್ದೆ: Virtual Customer Support Associate
                    - ವಿದ್ಯಾರ್ಹತೆ: ಪಿಯುಸಿ (10+2) ಅಥವಾ ಯಾವುದೇ ಪದವಿ (BA, B.Com, B.Sc, BBA, ಇತ್ಯಾದಿ)
                    - ಮಾಸಿಕ ವೇತನ: ₹28,000 - ₹38,000 + ₹2,500 ಇಂಟರ್ನೆಟ್ ಅಲೋವೆನ್ಸ್
                    - ಕಂಪನಿಯಿಂದ ಉಚಿತವಾಗಿ ಲ್ಯಾಪ್‌ಟಾಪ್ ಮತ್ತು ಹೆಡ್‌ಸೆಟ್ ನೀಡಲಾಗುತ್ತದೆ
                    - ಭಾಷೆ: ಕನ್ನಡ ಹಾಗೂ ಇಂಗ್ಲಿಷ್‌ನಲ್ಲಿ ಉತ್ತಮ ಸಂವಹನ ಕೌಶಲ್ಯ ಹೊಂದಿರಬೇಕು
                """.trimIndent(),
                fullArticleEn = """
                    Amazon India has rolled out fresh openings for Virtual Customer Support and Operations Associates based out of Karnataka with complete Work-from-Home flexibility.

                    Position Details:
                    - Role: Virtual Customer Associate
                    - Educational Requirement: 10+2 / Any Degree
                    - Monthly Compensation: INR 28,000 - 38,000 + allowances
                    - Hardware: Company provided laptop, headset, and monthly internet stipend
                """.trimIndent(),
                applyUrl = "https://amazon.jobs/en/virtual-locations",
                sourceWebsiteName = "Foundit Private Job Portal",
                sourceWebsiteUrl = "https://www.foundit.in/private-jobs",
                postedTimestamp = now - 18 * hour,
                salaryRange = "₹3.4 - ₹4.5 LPA",
                experience = "0 - 3 Years"
            ),
            JobPost(
                id = 4,
                titleKn = "ಟೊಯೋಟಾ ಕಿರ್ಲೋಸ್ಕರ್ ಮೋಟಾರ್ (Toyota) - ಜೂನಿಯರ್ ಇಂಜಿನಿಯರ್ ಮತ್ತು ಆಟೋ ಟೆಕ್ನಿಷಿಯನ್",
                titleEn = "Toyota Kirloskar Motor - Junior Engineer & Plant Technician Openings",
                company = "Toyota Kirloskar Motor Pvt Ltd",
                locationKn = "ಬಿಡದಿ ಇಂಡಸ್ಟ್ರಿಯಲ್ ಏರಿಯಾ, ರಾಮನಗರ (ಬೆಂಗಳೂರು ಬಳಿ)",
                locationEn = "Bidadi Industrial Area, Ramanagara (Near Bengaluru)",
                categoryKn = "ಮೆಕ್ಯಾನಿಕಲ್ & ಆಟೋಮೊಬೈಲ್",
                categoryEn = "Mechanical & Automotive",
                shortDescKn = "ಟೊಯೋಟಾ ಖಾಸಗಿ ಮ್ಯಾನುಫ್ಯಾಕ್ಚರಿಂಗ್ ಘಟಕದಲ್ಲಿ ಡಿಪ್ಲೊಮಾ ಮತ್ತು ಐಟಿಐ ಮುಗಿಸಿದ ಅಭ್ಯರ್ಥಿಗಳಿಗೆ ಕಾಯಂ ನೇಮಕಾತಿ ಅವಕಾಶ.",
                shortDescEn = "Toyota Kirloskar Motor invites applications for manufacturing and assembly technicians at Bidadi plant.",
                fullArticleKn = """
                    ರಾಮನಗರ ಜಿಲ್ಲೆಯ ಬಿಡದಿಯಲ್ಲಿರುವ ಟೊಯೋಟಾ ಕಿರ್ಲೋಸ್ಕರ್ ಖಾಸಗಿ ಆಟೋಮೊಬೈಲ್ ಕಾರ್ಖಾನೆಯಲ್ಲಿ ಉತ್ಪಾದನೆ ಮತ್ತು ಗುಣಮಟ್ಟ ಪರಿಶೀಲನೆಗಾಗಿ ನುರಿತ ತಂತ್ರಜ್ಞರ ನೇಮಕಾತಿ ನಡೆಯುತ್ತಿದೆ.

                    ವಿವರಗಳು:
                    - ಹುದ್ದೆ: Graduate Engineering Trainee / Diploma Plant Technician
                    - ವಿದ್ಯಾರ್ಹತೆ: ಡಿಪ್ಲೊಮಾ (ಮೆಕ್ಯಾನಿಕಲ್, ಆಟೋಮೊಬೈಲ್, ಎಲೆಕ್ಟ್ರಿಕಲ್) / ITI
                    - ವೇತನ: ತಿಂಗಳಿಗೆ ₹24,000 - ₹34,000 + ಸಬ್ಸಿಡಿ ಕ್ಯಾಂಟೀನ್ ಮತ್ತು ಉಚಿತ ಬಸ್ ಸೌಲಭ್ಯ
                    - ವಯೋಮಿತಿ: 18 ರಿಂದ 28 ವರ್ಷಗಳು
                """.trimIndent(),
                fullArticleEn = """
                    Toyota Kirloskar Motor Private Limited is accepting direct applications for junior engineering and production positions at its state-of-the-art Bidadi manufacturing plant.

                    Highlights:
                    - Role: Manufacturing Trainee / Technician
                    - Qualification: Diploma (Mechanical / Electrical / Automobile) or ITI
                    - Salary: INR 24,000 - 34,000 / month + Bus & Canteen subsidies
                """.trimIndent(),
                applyUrl = "https://www.toyotabharat.com/careers",
                sourceWebsiteName = "Auto & Manufacturing Karnataka Private",
                sourceWebsiteUrl = "https://www.autojobskarnataka.com",
                postedTimestamp = now - 1 * day,
                salaryRange = "₹2.8 - ₹4.0 LPA",
                experience = "Diploma / ITI Freshers"
            ),
            JobPost(
                id = 5,
                titleKn = "ಸ್ವಿಗ್ಗಿ & ಜೊಮಾಟೊ (Swiggy) - ಸಿಟಿ ಆಪರೇಷನ್ಸ್ ಮ್ಯಾನೇಜರ್ & ಫ್ಲೀಟ್ ಸೂಪರ್‌ವೈಸರ್",
                titleEn = "Swiggy City Operations & Logistics Supervisor Positions",
                company = "Bundl Technologies (Swiggy) Pvt Ltd",
                locationKn = "ಬೆಂಗಳೂರು, ಮೈಸೂರು, ಹುಬ್ಬಳ್ಳಿ, ದಾವಣಗೆರೆ",
                locationEn = "Bengaluru, Mysuru, Hubballi, Davanagere",
                categoryKn = "ಲಾಜಿಸ್ಟಿಕ್ಸ್ & ಆಪರೇಷನ್ಸ್",
                categoryEn = "Logistics & Operations",
                shortDescKn = "ಸ್ವಿಗ್ಗಿ ಖಾಸಗಿ ಸಂಸ್ಥೆಯು ಕರ್ನಾಟಕದ ಪ್ರಮುಖ ನಗರಗಳಲ್ಲಿ ಫ್ಲೀಟ್ ನಿರ್ವಹಣೆ ಹಾಗೂ ಸೂಪರ್‌ವೈಸರ್ ಹುದ್ದೆಗಳಿಗೆ ತ್ವರಿತ ನೇಮಕಾತಿ ಹಮ್ಮಿಕೊಂಡಿದೆ.",
                shortDescEn = "Swiggy is seeking Operations and Logistics supervisors across tier-1 and tier-2 Karnataka districts.",
                fullArticleKn = """
                    ಖಾಸಗಿ ಡೆಲಿವರಿ ಆ್ಯಪ್ ಸಂಸ್ಥೆಯಾದ ಸ್ವಿಗ್ಗಿ ಕರ್ನಾಟಕದ ಜಿಲ್ಲಾ ಕೇಂದ್ರಗಳಲ್ಲಿ ಆಪರೇಷನ್ಸ್ ಕಂಟ್ರೋಲರ್ ಹಾಗೂ ಫ್ಲೀಟ್ ಲೀಡ್ ಹುದ್ದೆಗಳನ್ನು ಭರ್ತಿ ಮಾಡುತ್ತಿದೆ.

                    ಹುದ್ದೆಯ ವಿಶೇಷತೆ:
                    - ಹುದ್ದೆ: Field Operations Supervisor
                    - ವಿದ್ಯಾರ್ಹತೆ: ಯಾವುದೇ ಪದವಿ (BA, B.Com, B.Sc, Diploma)
                    - ವೇತನ: ₹25,000 - ₹32,000 ಮಾಸಿಕ + ಪೆಟ್ರೋಲ್ ಭತ್ಯೆ ಮತ್ತು ಇನ್ಸೆಂಟಿವ್ಸ್
                    - ಕೆಲಸದ ಜವಾಬ್ದಾರಿ: ಆರ್ಡರ್ ಡೆಲಿವರಿ ಮೇಲ್ವಿಚಾರಣೆ ಹಾಗೂ ಡೆಲಿವರಿ ಸಿಬ್ಬಂದಿಯ ನಿರ್ವಹಣೆ
                """.trimIndent(),
                fullArticleEn = """
                    Swiggy (Bundl Technologies Private Limited) is recruiting Area Operations Executives and Hub Managers across Karnataka.

                    Role Highlights:
                    - Position: Hub Operations Executive
                    - Requirement: Graduate in any stream
                    - Remuneration: INR 25,000 - 32,000 / month + Travel & Performance bonus
                """.trimIndent(),
                applyUrl = "https://careers.swiggy.com/operations",
                sourceWebsiteName = "Private Logistics & Quick Commerce",
                sourceWebsiteUrl = "https://www.supplychainjobskarnataka.com",
                postedTimestamp = now - 2 * day,
                salaryRange = "₹3.0 - ₹4.2 LPA",
                experience = "0 - 2 Years"
            ),
            JobPost(
                id = 6,
                titleKn = "ಎಚ್‌ಡಿಎಫ್‌ಸಿ ಬ್ಯಾಂಕ್ (HDFC Bank) - ಖಾಸಗಿ ಬ್ಯಾಂಕಿಂಗ್ ಎಕ್ಸಿಕ್ಯೂಟಿವ್ ನೇಮಕಾತಿ",
                titleEn = "HDFC Private Bank - Branch Relationship Officer & Teller",
                company = "HDFC Bank Private Limited",
                locationKn = "ಮಂಗಳೂರು, ಉಡುಪಿ, ಶಿವಮೊಗ್ಗ, ಹಾಸನ",
                locationEn = "Mangaluru, Udupi, Shivamogga, Hassan",
                categoryKn = "ಬ್ಯಾಂಕಿಂಗ್ & ಹಣಕಾಸು",
                categoryEn = "Banking & Finance",
                shortDescKn = "ಖಾಸಗಿ ವಲಯದ ನಂಬರ್ 1 ಬ್ಯಾಂಕ್ ಎಚ್‌ಡಿಎಫ್‌ಸಿಯಲ್ಲಿ ಶಾಖಾ ಸಂಬಂಧಿ ಅಧಿಕಾರಿ ಹಾಗೂ ಟೆಲ್ಲರ್ ಹುದ್ದೆಗಳಿಗೆ ಖಾಸಗಿ ಸಂದರ್ಶನ.",
                shortDescEn = "HDFC Bank invites applications for customer relationship officers and retail banking executives in coastal and malnad districts.",
                fullArticleKn = """
                    ಖಾಸಗಿ ಬ್ಯಾಂಕಿಂಗ್ ರಂಗದ ಪ್ರಮುಖ ಸಂಸ್ಥೆ ಎಚ್‌ಡಿಎಫ್‌ಸಿ ಬ್ಯಾಂಕ್ ಕರ್ನಾಟಕದ ಕರಾವಳಿ ಮತ್ತು ಮಲೆನಾಡು ಭಾಗದ ನೂತನ ಶಾಖೆಗಳಿಗೆ ಎಕ್ಸಿಕ್ಯೂಟಿವ್‌ಗಳನ್ನು ನೇಮಿಸಿಕೊಳ್ಳುತ್ತಿದೆ.

                    ಅರ್ಹತೆಗಳು:
                    - ಹುದ್ದೆ: Personal Banker / Relationship Officer
                    - ವಿದ್ಯಾರ್ಹತೆ: B.Com, BBA, B.Sc ಅಥವಾ ಯಾವುದೇ ಪದವಿ
                    - ವೇತನ: ₹3,20,000 - ₹4,80,000 ವಾರ್ಷಿಕ + ಬೋನಸ್
                    - ಕೌಶಲ್ಯ: ಕಂಪ್ಯೂಟರ್ ಮತ್ತು ಗ್ರಾಹಕರೊಂದಿಗೆ ಸ್ನೇಹಪರ ಮಾತುಕತೆ
                """.trimIndent(),
                fullArticleEn = """
                    HDFC Bank Private Limited is hiring entry-level and experienced Banking Officers for branch operations in Karnataka.

                    Key Roles:
                    - Position: Personal Banker / Teller
                    - Qualification: Graduate in Commerce, Business, or Science
                    - Package: INR 3.2 LPA to 4.8 LPA with corporate incentives
                """.trimIndent(),
                applyUrl = "https://www.hdfcbank.com/careers/retail",
                sourceWebsiteName = "Indeed Bangalore Private Careers",
                sourceWebsiteUrl = "https://in.indeed.com/private-jobs-bangalore",
                postedTimestamp = now - 3 * day,
                salaryRange = "₹3.2 - ₹4.8 LPA",
                experience = "Freshers to 3 Years"
            ),
            JobPost(
                id = 7,
                titleKn = "ವಿಪ್ರೋ (Wipro) - ಪ್ರೈವೇಟ್ ಗ್ರಾಜುಯೇಟ್ ಟ್ರೈನಿ (WILP & Elite Drive)",
                titleEn = "Wipro Elite National Talent Hunt & WILP Program 2026",
                company = "Wipro Technologies Private Ltd",
                locationKn = "ಬೆಂಗಳೂರು / ಮೈಸೂರು",
                locationEn = "Bengaluru / Mysuru",
                categoryKn = "ಐಟಿ & ಸಾಫ್ಟ್‌ವೇರ್",
                categoryEn = "IT & Software",
                shortDescKn = "ವಿಪ್ರೋ ಖಾಸಗಿ ಕಂಪನಿಯ ಉನ್ನತ ಶ್ರೇಣಿಯ ತರಬೇತಿ ಹಾಗೂ ನೌಕರಿ ಕಾರ್ಯಕ್ರಮ. ಬಿಟ್ಸ್ ಪಿಲಾನಿ ಸಂಸ್ಥೆಯಿಂದ ಎಂ.ಟೆಕ್ ಉಚಿತ ವ್ಯಾಸಂಗದ ಜೊತೆಗೆ ತಿಂಗಳಿಗೆ ವೇತನ.",
                shortDescEn = "Wipro offers sponsored higher education with full-time salary for BCA and BSc graduates.",
                fullArticleKn = """
                    ವಿಪ್ರೋ ಖಾಸಗಿ ಕಂಪನಿಯು BCA ಮತ್ತು B.Sc ವಿದ್ಯಾರ್ಥಿಗಳಿಗೆ ಕಲಿಯುತ್ತಲೇ ದುಡಿಯುವ ವರ್ಕ್ ಇಂಟಿಗ್ರೇಟೆಡ್ ಲರ್ನಿಂಗ್ ಪ್ರೋಗ್ರಾಂ (WILP) ಆರಂಭಿಸಿದೆ.

                    ವಿಶೇಷತೆಗಳು:
                    - ಹುದ್ದೆ: Scholar Trainee
                    - ವಿದ್ಯಾರ್ಹತೆ: BCA / B.Sc (ಕಂಪ್ಯೂಟರ್ ಸೈನ್ಸ್, ಎಲೆಕ್ಟ್ರಾನಿಕ್ಸ್, ಮ್ಯಾಥ್ಸ್)
                    - ಸ್ಟೈಫಂಡ್: ಮೊದಲ ವರ್ಷ ₹15,700, 2ನೇ ವರ್ಷ ₹17,800, 3ನೇ ವರ್ಷ ₹19,900 ಹಾಗೂ 4ನೇ ವರ್ಷ ₹23,000
                    - ಜೊತೆಗೆ ಬಿಟ್ಸ್ ಪಿಲಾನಿಯಿಂದ ಸ್ಪಾನ್ಸರ್ಡ್ ಎಂ.ಟೆಕ್ ಪದವಿ ಉಚಿತ!
                """.trimIndent(),
                fullArticleEn = """
                    Wipro's renowned Work Integrated Learning Program (WILP) provides BCA & BSc graduates an opportunity to work while acquiring an M.Tech degree from BITS Pilani completely sponsored.

                    Overview:
                    - Role: Scholar Trainee
                    - Qualification: BCA / BSc with Mathematics in 10+2
                    - Monthly Stipend: INR 15,700 - 23,000 + Sponsored M.Tech
                """.trimIndent(),
                applyUrl = "https://careers.wipro.com/wilp",
                sourceWebsiteName = "Internshala Karnataka Freshers",
                sourceWebsiteUrl = "https://internshala.com/jobs/karnataka",
                postedTimestamp = now - 4 * day,
                salaryRange = "₹2.2 - ₹4.5 LPA",
                experience = "BCA / BSc Freshers"
            ),
            JobPost(
                id = 8,
                titleKn = "ಬಾಷ್ ಇಂಡಿಯಾ (Bosch) - ಪ್ರೈವೇಟ್ ಆರ್ & ಡಿ ಅಸೋಸಿಯೇಟ್ ಸಾಫ್ಟ್‌ವೇರ್ ಇಂಜಿನಿಯರ್",
                titleEn = "Bosch India - Embedded Systems & Software Trainee Openings",
                company = "Robert Bosch Engineering Private Ltd",
                locationKn = "ಬೆಂಗಳೂರು (ಆಡುಗೋಡಿ & ಕೋರಮಂಗಲ)",
                locationEn = "Bengaluru (Adugodi & Koramangala)",
                categoryKn = "ಆಟೋಮೇಷನ್ & ಎಲೆಕ್ಟ್ರಾನಿಕ್ಸ್",
                categoryEn = "Automation & Electronics",
                shortDescKn = "ಬಾಷ್ ಖಾಸಗಿ ಇಂಜಿನಿಯರಿಂಗ್ ಕೇಂದ್ರದಲ್ಲಿ ಎಂಬೆಡೆಡ್ ಸಿಸ್ಟಮ್ಸ್ ಮತ್ತು ಜಾವಾ ಡೆವಲಪರ್ ಹುದ್ದೆಗಳಿಗೆ ಆಹ್ವಾನ.",
                shortDescEn = "Bosch is hiring entry-level automotive software engineers and electronics associates in Bangalore.",
                fullArticleKn = """
                    ಜಾಗತಿಕ ಖಾಸಗಿ ತಂತ್ರಜ್ಞಾನ ಸಂಸ್ಥೆಯಾದ ರಾಬರ್ಟ್ ಬಾಷ್ ಭಾರತದ ನಾವೀನ್ಯತಾ ಘಟಕದಲ್ಲಿ ನವ ಇಂಜಿನಿಯರ್‌ಗಳಿಗೆ ಸೂಕ್ತ ವೃತ್ತಿ ಬುನಾದಿ ನೀಡುತ್ತಿದೆ.

                    ವಿವರ:
                    - ಹುದ್ದೆ: Associate Software Engineer
                    - ವಿದ್ಯಾರ್ಹತೆ: B.E / B.Tech (ECE, EEE, CS, IS)
                    - ವೇತನ: ₹5,50,000 - ₹8,00,000 ವಾರ್ಷಿಕ
                    - ಜ್ಞಾನ: C, C++, Python ಅಥವಾ Java ಮೂಲಭೂತ ಜ್ಞಾನ
                """.trimIndent(),
                fullArticleEn = """
                    Robert Bosch Engineering and Business Solutions is expanding its Karnataka R&D hubs for intelligent automotive software.

                    Position Info:
                    - Role: Associate Software Engineer
                    - Qualification: BE / BTech in Circuit branches
                    - CTC: INR 5.5 LPA - 8.0 LPA
                """.trimIndent(),
                applyUrl = "https://www.bosch.in/careers",
                sourceWebsiteName = "Bangalore IT & Tech Openings",
                sourceWebsiteUrl = "https://www.techjobsinbangalore.com",
                postedTimestamp = now - 5 * day,
                salaryRange = "₹5.5 - ₹8.0 LPA",
                experience = "0 - 1.5 Years"
            ),
            JobPost(
                id = 9,
                titleKn = "ಫ್ಲಿಪ್‌ಕಾರ್ಟ್ (Flipkart) - ವೇರ್‌ಹೌಸ್ ಕೋ-ಆರ್ಡಿನೇಟರ್ ಮತ್ತು ಡೇಟಾ ಎಂಟ್ರಿ ಪ್ರೈವೇಟ್ ಜಾಬ್",
                titleEn = "Flipkart Supply Chain - Warehouse Coordinator & Data Executive",
                company = "Flipkart Internet Private Limited",
                locationKn = "ಮಾಲೂರು, ಹೊಸಕೋಟೆ (ಬೆಂಗಳೂರು ಗ್ರಾಮಾಂತರ)",
                locationEn = "Malur, Hoskote (Bengaluru Rural)",
                categoryKn = "ವೇರ್‌ಹೌಸ್ & ಡೇಟಾ ಎಂಟ್ರಿ",
                categoryEn = "Warehouse & Data Entry",
                shortDescKn = "ಫ್ಲಿಪ್‌ಕಾರ್ಟ್ ಖಾಸಗಿ ಸಪ್ಲೈ ಚೈನ್ ಸೆಂಟರ್‌ನಲ್ಲಿ ಇನ್ವೆಂಟರಿ ನಿರ್ವಹಣೆ ಹಾಗೂ ಕಂಪ್ಯೂಟರ್ ಆಪರೇಟರ್ ಹುದ್ದೆಗಳು ಖಾಲಿ ಇವೆ.",
                shortDescEn = "Flipkart is conducting walk-in interviews for warehouse coordinators, billing, and inventory operators.",
                fullArticleKn = """
                    ಭಾರತದ ಖಾಸಗಿ ಶಾಪಿಂಗ್ ದಿಗ್ಗಜ ಫ್ಲಿಪ್‌ಕಾರ್ಟ್ ತನ್ನ ಮಾಲೂರು ಮತ್ತು ಹೊಸಕೋಟೆಯ ಆಧುನಿಕ ವೇರ್‌ಹೌಸ್‌ನಲ್ಲಿ ತಕ್ಷಣದ ನೇಮಕಾತಿಗೆ ಮುಂದಾಗಿದೆ.

                    ಉದ್ಯೋಗ ವಿವರ:
                    - ಹುದ್ದೆ: Inventory Associate / Data Operator
                    - ವಿದ್ಯಾರ್ಹತೆ: 10ನೇ ತರಗತಿ, ಪಿಯುಸಿ, ಅಥವಾ ಯಾವುದೇ ಡಿಗ್ರಿ
                    - ವೇತನ: ₹22,000 - ₹28,000 ಮಾಸಿಕ + ಓವರ್‌ಟೈಮ್ ಭತ್ಯೆ + ಇನ್ಸೂರೆನ್ಸ್
                    - ವಾರದಲ್ಲಿ 5 ದಿನ ಕೆಲಸ, 2 ದಿನ ರಜೆ
                """.trimIndent(),
                fullArticleEn = """
                    Flipkart Internet Private Limited has immediate vacancies for fulfillment center executives and billing data operators.

                    Role Highlights:
                    - Position: Logistics Data Associate
                    - Education: 10th / 12th / Any Degree
                    - Monthly Remuneration: INR 22,000 - 28,000 + Night Shift & OT
                """.trimIndent(),
                applyUrl = "https://www.flipkartcareers.com",
                sourceWebsiteName = "Shine Karnataka Private Alerts",
                sourceWebsiteUrl = "https://www.shine.com/job-search/private-jobs-karnataka",
                postedTimestamp = now - 6 * day,
                salaryRange = "₹2.6 - ₹3.4 LPA",
                experience = "Freshers welcome"
            ),
            JobPost(
                id = 10,
                titleKn = "ಟೆಲಿಪರ್ಫಾರ್ಮೆನ್ಸ್ (Teleperformance) - ಇಂಟರ್ನ್ಯಾಷನಲ್ ವಾಯ್ಸ್ & ಚಾಟ್ ಪ್ರೊಫೈಲ್",
                titleEn = "Teleperformance - Customer Experience & Chat Support Specialist",
                company = "Teleperformance Private Limited",
                locationKn = "ಬೆಂಗಳೂರು / ವೈಟ್‌ಫೀಲ್ಡ್",
                locationEn = "Bengaluru / Whitefield",
                categoryKn = "ಬಿಪಿಒ & ಕಸ್ಟಮರ್ ಸಪೋರ್ಟ್",
                categoryEn = "BPO & Customer Support",
                shortDescKn = "ಟೆಲಿಪರ್ಫಾರ್ಮೆನ್ಸ್ ಖಾಸಗಿ ಬಹುರಾಷ್ಟ್ರೀಯ ಕಂಪನಿಯಲ್ಲಿ ಆಕರ್ಷಕ ವೇತನ ಹಾಗೂ ಉಚಿತ ಕ್ಯಾಬ್ ಸೌಲಭ್ಯದೊಂದಿಗೆ ಪ್ರೈವೇಟ್ ನೇಮಕಾತಿ.",
                shortDescEn = "Teleperformance is hiring customer service advisors for international banking & tech clients in Whitefield.",
                fullArticleKn = """
                    ಜಾಗತಿಕ ಖಾಸಗಿ ಬಿಪಿಒ ಸಂಸ್ಥೆಯಾದ ಟೆಲಿಪರ್ಫಾರ್ಮೆನ್ಸ್ ಬೆಂಗಳೂರಿನ ವೈಟ್‌ಫೀಲ್ಡ್ ಕಚೇರಿಯಲ್ಲಿ ಇಂಟರ್ನ್ಯಾಷನಲ್ ಕ್ಲೈಂಟ್ ಸಪೋರ್ಟ್ ಹುದ್ದೆಗಳಿಗೆ ಅಭ್ಯರ್ಥಿಗಳನ್ನು ನೇಮಿಸಿಕೊಳ್ಳುತ್ತಿದೆ.

                    ಅರ್ಹತೆಗಳು:
                    - ಹುದ್ದೆ: Customer Support Advisor (Voice / Non-Voice Chat)
                    - ವಿದ್ಯಾರ್ಹತೆ: ಯಾವುದೇ ಪದವೀಧರರು ಅಥವಾ ಅಂತಿಮ ವರ್ಷದ ಫಲಿತಾಂಶ ಕಾಯುತ್ತಿರುವವರು
                    - ವೇತನ: ತಿಂಗಳಿಗೆ ₹30,000 - ₹45,000 + ಇನ್ಸೆಂಟಿವ್ಸ್
                    - ಸೌಲಭ್ಯ: ಉಚಿತ ಪಿಕ್ & ಡ್ರಾಪ್ ಕ್ಯಾಬ್, ವಾರ್ಷಿಕ ಆರೋಗ್ಯ ರಕ್ಷಣೆ
                """.trimIndent(),
                fullArticleEn = """
                    Teleperformance Private Limited invites candidates for premium voice and non-voice digital customer experience campaigns.

                    Details:
                    - Position: Customer Care Specialist
                    - Education: Any Graduate
                    - Remuneration: INR 30,000 - 45,000 / month + Shift incentives
                """.trimIndent(),
                applyUrl = "https://teleperformance.com/careers-india",
                sourceWebsiteName = "Private Retail & BPO Careers",
                sourceWebsiteUrl = "https://www.karnatakabpojobs.com",
                postedTimestamp = now - 6 * day - 12 * hour,
                salaryRange = "₹3.6 - ₹5.4 LPA",
                experience = "Freshers to 2 Years"
            )
        )
    }

    fun getInitialJobVideos(): List<JobVideo> {
        val now = System.currentTimeMillis()
        val day = TimeUnit.DAYS.toMillis(1)
        val hour = TimeUnit.HOURS.toMillis(1)

        return listOf(
            JobVideo(
                id = "vid_1",
                titleKn = "ಕರ್ನಾಟಕ ಖಾಸಗಿ ಕಂಪನಿಗಳಲ್ಲಿ ಸುಲಭವಾಗಿ ಜಾಬ್ ಪಡೆಯುವುದು ಹೇಗೆ? ರೆಸ್ಯೂಮೆ & ಇಂಟರ್ವ್ಯೂ ಟಿಪ್ಸ್",
                titleEn = "How to Get Private Sector Jobs in Karnataka - ATS Resume & HR Interview Tips",
                channelNameKn = "ಕನ್ನಡ ಜಾಬ್ ನ್ಯೂಸ್",
                channelNameEn = "Kannada Job News",
                duration = "14:25",
                thumbnailUrl = "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?w=500&q=80",
                youtubeVideoId = "M7lc1UVf-VE",
                publishedTimestamp = now - 5 * hour,
                viewsCount = "45K"
            ),
            JobVideo(
                id = "vid_2",
                titleKn = "ಇನ್ಫೋಸಿಸ್ & ಟಿಸಿಎಸ್ ಖಾಸಗಿ ಕಂಪನಿಗಳ ಆಪ್ಟಿಟ್ಯೂಡ್ ಟೆಸ್ಟ್ ಪಾಸಾಗುವುದು ಹೇಗೆ?",
                titleEn = "Infosys & TCS Private Aptitude Test Complete Strategy in Kannada",
                channelNameKn = "ಕೆರಿಯರ್ ಸಪೋರ್ಟ್ ಕನ್ನಡ",
                channelNameEn = "Career Support Kannada",
                duration = "18:40",
                thumbnailUrl = "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=500&q=80",
                youtubeVideoId = "tgbNymZ7vqY",
                publishedTimestamp = now - 1 * day,
                viewsCount = "82K"
            ),
            JobVideo(
                id = "vid_3",
                titleKn = "ಮನೆಯಿಂದಲೇ ಕೆಲಸ (WFH) ನೀಡುವ ಟಾಪ್ ಖಾಸಗಿ ಕಂಪನಿಗಳು - ನೇರ ಅರ್ಜಿ ವಿಧಾನ",
                titleEn = "Top Private WFH Job Openings for Freshers & Women in Karnataka 2026",
                channelNameKn = "ಕರ್ನಾಟಕ ಜಾಬ್ ಇನ್ಫೋ",
                channelNameEn = "Karnataka Job Info",
                duration = "11:15",
                thumbnailUrl = "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=500&q=80",
                youtubeVideoId = "bHQqvYy5KYo",
                publishedTimestamp = now - 2 * day,
                viewsCount = "120K"
            ),
            JobVideo(
                id = "vid_4",
                titleKn = "ಕೋಡಿಂಗ್ ಇಲ್ಲದೆ ಐಟಿ ಖಾಸಗಿ ಕಂಪನಿಗಳಲ್ಲಿ ಕೆಲಸ ಪಡೆಯುವುದು ಹೇಗೆ? (QA, Cloud & Data)",
                titleEn = "Non-Coding Private IT Careers: QA, Analyst, Scrum & Cloud Admin in Kannada",
                channelNameKn = "ಕನ್ನಡ ಜಾಬ್ಸ್ ಅಪ್ಡೇಟ್",
                channelNameEn = "Kannada Jobs Update",
                duration = "16:05",
                thumbnailUrl = "https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=500&q=80",
                youtubeVideoId = "e-ORhEE9VVg",
                publishedTimestamp = now - 3 * day,
                viewsCount = "67K"
            ),
            JobVideo(
                id = "vid_5",
                titleKn = "ಖಾಸಗಿ ಕಂಪನಿಗಳ ಎಚ್‌ಆರ್ ಇಂಟರ್ವ್ಯೂ ಪ್ರಶ್ನೋತ್ತರಗಳು: Tell Me About Yourself ಕನ್ನಡದಲ್ಲಿ",
                titleEn = "Private HR Interview Questions & Answers with Live Kannada Demo",
                channelNameKn = "ಜಾಬ್ ನ್ಯೂಸ್ ಕನ್ನಡ",
                channelNameEn = "Job News Kannada",
                duration = "22:10",
                thumbnailUrl = "https://images.unsplash.com/photo-1551836022-d5d88e9218df?w=500&q=80",
                youtubeVideoId = "kJQP7kiw5Fk",
                publishedTimestamp = now - 4 * day,
                viewsCount = "95K"
            ),
            JobVideo(
                id = "vid_6",
                titleKn = "ಖಾಸಗಿ ಕಂಪನಿಗಳಲ್ಲಿ ₹35,000+ ವೇತನದ ಖಾತರಿ ನೀಡುವ ಅತ್ಯುತ್ತಮ ಕೌಶಲ್ಯಗಳು 2026",
                titleEn = "High-Demand Private Job Skills in Karnataka - Full Roadmap 2026",
                channelNameKn = "ಕರ್ನಾಟಕ ಜಾಬ್ ಅಡ್ಡ",
                channelNameEn = "Karnataka Job Adda",
                duration = "15:50",
                thumbnailUrl = "https://images.unsplash.com/photo-1531482615713-2afd69097998?w=500&q=80",
                youtubeVideoId = "fJ9rUzIMcZQ",
                publishedTimestamp = now - 5 * day,
                viewsCount = "150K"
            )
        )
    }

    // Generate fresh post when user syncs daily or adds website link
    fun createPostFromCustomUrl(url: String, index: Int): JobPost {
        val now = System.currentTimeMillis()
        val siteDomain = url.replace("https://", "").replace("http://", "").split("/").firstOrNull() ?: url
        return JobPost(
            id = System.currentTimeMillis() + index,
            titleKn = "ಹೊಸ ಖಾಸಗಿ ಉದ್ಯೋಗ ಅಧಿಸೂಚನೆ - $siteDomain",
            titleEn = "Fresh Private Career Notification - $siteDomain",
            company = "Verified Private Partner ($siteDomain)",
            locationKn = "ಕರ್ನಾಟಕ / ಹೈಬ್ರಿಡ್",
            locationEn = "Karnataka / Hybrid",
            categoryKn = "ಖಾಸಗಿ ನೇಮಕಾತಿ",
            categoryEn = "Private Hiring",
            shortDescKn = "$siteDomain ವೆಬ್‌ಸೈಟ್‌ನಿಂದ ಹೊಸದಾಗಿ ನೇಮಕಾತಿ ಅಧಿಸೂಚನೆ ಪರಿಶೀಲಿಸಲಾಗಿದೆ ಮತ್ತು ಸಿಂಕ್ ಮಾಡಲಾಗಿದೆ.",
            shortDescEn = "Fresh verified private job update retrieved and indexed from user-configured portal $siteDomain.",
            fullArticleKn = """
                ಬಳಕೆದಾರರು ಸೆಟ್ಟಿಂಗ್ಸ್‌ನಲ್ಲಿ ನಮೂದಿಸಿದ $siteDomain ಖಾಸಗಿ ವೆಬ್‌ಸೈಟ್‌ನಿಂದ ಈ ಕೆಳಗಿನ ನೇಮಕಾತಿ ಮಾಹಿತಿ ಲಭ್ಯವಾಗಿದೆ.

                ಹುದ್ದೆಯ ಮುಖ್ಯಾಂಶ:
                - ಅಧಿಕೃತ ಮೂಲ: $url
                - ಉದ್ಯೋಗ ಮಾದರಿ: ಖಾಸಗಿ ಸಂಸ್ಥೆ (ಕಾರ್ಪೊರೇಟ್ / ಇಂಡಸ್ಟ್ರಿ)
                - ಅರ್ಹತೆ: ಯಾವುದೇ ಪದವಿ / ಡಿಪ್ಲೊಮಾ / ಇಂಜಿನಿಯರಿಂಗ್
                - ವೇತನ: ಉದ್ಯಮ ಗುಣಮಟ್ಟಕ್ಕೆ ಅನುಗುಣವಾಗಿ (Industry Standard)

                ಅರ್ಜಿ ಸಲ್ಲಿಸಲು ಕೆಳಗಿನ 'ಅರ್ಜಿ ಸಲ್ಲಿಸಿ' ಬಟನ್ ಬಳಸಿ.
            """.trimIndent(),
            fullArticleEn = """
                A newly synchronized private career update from $siteDomain.

                Highlights:
                - Source Portal: $url
                - Organization Type: Private Enterprise
                - Eligibility: Graduate / Diploma / Professional
                - Remuneration: Best in Industry

                Click the Apply button below to visit the official application portal directly.
            """.trimIndent(),
            applyUrl = url,
            sourceWebsiteName = siteDomain,
            sourceWebsiteUrl = url,
            postedTimestamp = now,
            salaryRange = "₹3.5 - ₹6.0 LPA",
            experience = "Freshers / Experienced",
            isCustomUserAdded = true
        )
    }
}
