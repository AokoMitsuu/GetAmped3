	� G  ��       �UAo�6>ү�������+:C�N�-���A����-���Ptj'0wr�h�nة�avYw-0��p���=RRl4�d�}�}�����M>U<4�>)q�Ms���sձ�8ͅTk0Uc�ٹj_��B	��*z�:N�1r�j��Dl�*���f�W��Ƕ�v����'�'|pH��c�<��Ӣ��f���V�U?��x
|�c_����b�����S̈́4���m��|��N�3%c֨�H�R�=E�	�-�v;g�s��N������$�<S�<����~"��>��g�|ZACA�ƿ�-����_.�}o[O@��[=���+G�X��X��E��Y����?)&�8����D�HDο�-�%�B(��d�6�0�.r��
9���ҩ�O.�O�D�Z�>Z���Z.Wxꡦ�NVNn���4Ƿg��~�ZA�Xb p�5�đ�g#a�C3����6;P2+�q��;@��i�[�1�Ơe}�o��x��m}9�F��>�#���� 7=�im�rDX"2�d�a�t~������b��b�+����b����?�{�������ų���ų��_��ί3���'+C��h2U<*�Yp�v��Npϐ��"\�4<��q̟N�2V4*t E�Prp:�JQ66��A9�Ɉl���v�$X݌&ن�a*XQC�ۛ�����}�ׅ�_�ܞ�c��ދ��{+�C�6�$I<�/칺���r��*h���,��Q1Z���L��@��:pm�#!T��^m����`�ږ�7<�=%�R[VHS��Ak[�D�*X8�?�ܩL0���/�N�5fg�@�	� �!�����`���=��]Sg�Э�>S[�S���w��$l�dJ1��K8�u�^��&l��.�X+6"�!�'􇁑v�� f4W՜��rV�i��8I��y宥�F��2   d  Lga2/stage/AmpedScript; e Lkotori/gfx/KGLScriptModel; ele_ball start approachfloat broken
 H 0 3 setShadowState
 d m 3 	playSound
 d o 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d q rD /efx/electric/ele_ball3.scm�            ?�  ?�  ?�               start2 setJudgmentApproachFloat (F)V
 f v wA�   getArgv ()Ljava/lang/Object;
 f z { getPosition ()Lkotori/geom/Position;
 f } ~  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � cos (F)F
 H � � sin
 H � �	 � Q  Lkotori/geom/Position; � dir	 � � 	 � S 	 � R  (FFF)Lkotori/geom/Vector3D;
 � + � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D
 � � � Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � { getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � � 	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d q �D "/accs/thunder_rod/tr_Fx_attack.scm�            ?�  ?�  >���             attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �x� �D #/accs/thunder_rod/tr_Fx_attack2.scm�            ?��?��?��            � hit_ee  d� ^    N  �       ,�>�         (IIFF)V
 d o �?���?@   clearJudgmentKind
 f � 1 lockItemScript (Z)V
 f � � g startScript (Ljava/lang/String;)Z
 � � � 
start2_end kill
 f � 1 
stopScript
 f � 1 f h g i 1  /*d� lW*� nW* ܶ pW*tu� s� * ȶ lW*y� xW�  j 1	  �*� |L*� M+,� �W+� �,� �� �j+� �,� �� �jfF+� �,� �� �j+� �,� �� �jb8+� �8+%� �W+� �� �� ��8+� �� �� �� �Zd�8�:� �:� �W*� �W*�� �W*��� �W*� �W*��� �W*�� lW*y� xW�  k 1  ,*� �W*� �W� � � ö �W*� lW*� �W*� �W� �x6                        �  